package com.interaction.example.hateoas.simple;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;

import java.io.InputStreamReader;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import com.temenos.interaction.media.hal.MediaType;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

/**
 * Test that more than one Get command can be called from a resource. Check that
 * any stored ODataProducer remains present.
 */
public class MultipleGetITCase extends JerseyTest {

	public MultipleGetITCase() throws Exception {
		super();
	}

	@Before
	public void initTest() {
		webResource = Client.create().resource(ConfigurationHelper.getTestEndpointUri(Configuration.TEST_ENDPOINT_URI));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetTwiceNote() {
		// This resource uses a special test command that records how many times
		// it has been called. Note ID irrelevant.
		ClientResponse response = webResource.path("/twiceGetNote(1)").accept(MediaType.APPLICATION_HAL_JSON)
				.get(ClientResponse.class);
		assertEquals(Response.Status.Family.SUCCESSFUL, Response.Status.fromStatusCode(response.getStatus())
				.getFamily());

		RepresentationFactory representationFactory = new StandardRepresentationFactory();
		ReadableRepresentation resource = representationFactory.readRepresentation(MediaType.APPLICATION_HAL_JSON.toString(),new InputStreamReader(response
				.getEntityInputStream()));
		Map<String, Object> props = resource.getProperties();

		// Check result correct.
		assertEquals("1", props.get("noteID"));
		assertEquals("Called count = 2", props.get("body"));
	}
}
