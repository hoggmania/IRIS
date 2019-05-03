package com.temenos.interaction.authorization.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;

import com.temenos.interaction.authorization.exceptions.AuthorizationException;
import com.temenos.interaction.core.MultivaluedMapImpl;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.ResourceState;

/**
 * The Class AuthorizationCommandTest.
 */
public class PostSelectCommandTest extends AbstractAuthorizationTest {

	/**
	 * Check that thrown if selectDone not set.
	 */
	@Test
	public void testNoSelectDoneThrows() {

		PostSelectCommand command = new PostSelectCommand();

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
	 * Check that no selecting or throw if selectDone true.
	 */
	@Test
	public void testSelectDoneTrue() {

		PostSelectCommand command = new PostSelectCommand();

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));

		// Set the flag
		ctx.setAttribute(AuthorizationAttributes.SELECT_DONE_ATTRIBUTE, Boolean.TRUE);

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
}
