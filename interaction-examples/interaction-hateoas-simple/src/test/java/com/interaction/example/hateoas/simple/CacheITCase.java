package com.interaction.example.hateoas.simple;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import com.temenos.interaction.media.hal.MediaType;

/**
 * This test checks that queries are returned from cache when appropriate
 * 
 * @author amcguinness
 */
public class CacheITCase extends JerseyTest {

	public CacheITCase() throws Exception {
		super();
	}

	@Before
	public void initTest() {
		// -DTEST_ENDPOINT_URI={someurl} to test with external server
		webResource = Client.create().resource(ConfigurationHelper.getTestEndpointUri(Configuration.TEST_ENDPOINT_URI));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testNoteCollectionCaching() {
		// The collection is defined not to be cacheable
		ClientResponse response = webResource.path("/notes").accept(MediaType.APPLICATION_HAL_JSON)
				.get(ClientResponse.class);
		assertEquals(Response.Status.Family.SUCCESSFUL, Response.Status.fromStatusCode(response.getStatus())
				.getFamily());
		String collectionCacheHeader = response.getHeaders().getFirst(HttpHeaders.CACHE_CONTROL);
		assertNull(collectionCacheHeader);
	}
	
	@Test
	public void testNoteCaching() {
		// individual notes are defined to have a max-age.
		ClientResponse itemResponse = webResource.path("/notes/4").accept(MediaType.APPLICATION_HAL_JSON)
				.get(ClientResponse.class);
		assertEquals(Response.Status.Family.SUCCESSFUL, Response.Status.fromStatusCode(itemResponse.getStatus())
				.getFamily());
		String cacheHeader = itemResponse.getHeaders().getFirst(HttpHeaders.CACHE_CONTROL);
		assertEquals("max-age=120", cacheHeader);
		
		
		// re-requesting the same note should not hit the backend (which has a 2000ms delay baked in).
		long start = System.currentTimeMillis();
		ClientResponse repeatedResponse = webResource.path("/notes/4").accept(MediaType.APPLICATION_HAL_JSON)
				.get(ClientResponse.class);
		assertEquals(Response.Status.Family.SUCCESSFUL, Response.Status.fromStatusCode(repeatedResponse.getStatus())
				.getFamily());
		long finish = System.currentTimeMillis();
		assertTrue(finish < (1000 + start));
		System.out.println( "request took " + (finish-start) + "ms" );
	}

	@Test
	public void testProfile() {
		// The profile is cacheable
		ClientResponse response = webResource.path("/profile").accept(MediaType.APPLICATION_HAL_JSON)
				.get(ClientResponse.class);
		assertEquals(Response.Status.Family.SUCCESSFUL, Response.Status.fromStatusCode(response.getStatus())
				.getFamily());
		String cacheHeader = response.getHeaders().getFirst(HttpHeaders.CACHE_CONTROL);
		assertEquals("max-age=240", cacheHeader);
	}
}
