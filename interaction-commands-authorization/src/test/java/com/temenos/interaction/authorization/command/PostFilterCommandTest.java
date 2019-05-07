package com.temenos.interaction.authorization.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;
import org.odata4j.producer.ODataProducer;

import com.temenos.interaction.authorization.exceptions.AuthorizationException;
import com.temenos.interaction.commands.odata.ODataAttributes;
import com.temenos.interaction.core.MultivaluedMapImpl;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.odataext.odataparser.ODataParser;

/**
 * The Class AuthorizationCommandTest.
 */
public class PostFilterCommandTest extends AbstractAuthorizationTest {

	/**
	 * Check that thrown if filterDone not set.
	 */
	@Test
	public void testNoFilterDoneThrows() {

		PostFilterCommand command = new PostFilterCommand();

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));
		boolean threw = false;
		try {
			command.execute(ctx);
		} catch (AuthorizationException e) {
			threw = true;
		} catch (Exception e) {
		}

		// Should throw.
		assertTrue(threw);
	}

	/**
	 * Check that no filtering or throw if filterDone true.
	 */
	@Test
	public void testFilterDoneTrue() {

		PostFilterCommand command = new PostFilterCommand();

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));

		// Set the flag
		ctx.setAttribute(AuthorizationAttributes.FILTER_DONE_ATTRIBUTE, Boolean.TRUE);

		boolean threw = false;
		try {
			command.execute(ctx);
		} catch (AuthorizationException e) {
			threw = true;
		} catch (Exception e) {
		}

		// Should not throw.
		assertFalse(threw);
	}

	/**
	 * Check that filtering is done if filterDone false.
	 */
	@Test
	public void testFilterDoneFalse() {

		PostFilterCommand command = new PostFilterCommand();

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));

		// Set the flag
		ctx.setAttribute(AuthorizationAttributes.FILTER_DONE_ATTRIBUTE, Boolean.FALSE);

		// Set up filter
		ctx.getQueryParameters().add(ODataParser.FILTER_KEY, "test");

		// Provide a producer
		ODataProducer producer = mock(ODataProducer.class);
		ctx.setAttribute(ODataAttributes.O_DATA_PRODUCER_ATTRIBUTE, producer);

		boolean threw = false;
		try {
			command.execute(ctx);
		} catch (AuthorizationException e) {
			threw = true;
		} catch (Exception e) {
		}

		// Should not throw.
		assertFalse(threw);

		// Should have changed filterDone state.
		assertEquals(Boolean.TRUE, (Boolean) ctx.getAttribute(AuthorizationAttributes.FILTER_DONE_ATTRIBUTE));

		// Check filtering has been done.
		// TODO implement check
	}

	/**
	 * Check that if filterDone false and no producer is available throws.
	 */
	@Test
	public void testFilterDoneFalseNoProducer() {

		PostFilterCommand command = new PostFilterCommand();

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));

		// Set the flag
		ctx.setAttribute(AuthorizationAttributes.FILTER_DONE_ATTRIBUTE, Boolean.FALSE);

		// Set up filter
		ctx.getQueryParameters().add(ODataParser.FILTER_KEY, "test");

		boolean threw = false;
		try {
			command.execute(ctx);
		} catch (AuthorizationException e) {
			threw = true;
		} catch (Exception e) {
		}

		// Should not throw.
		assertTrue(threw);
	}

}
