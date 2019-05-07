package com.temenos.interaction.authorization.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;

import com.temenos.interaction.authorization.mock.MockAuthorizationBean;
import com.temenos.interaction.core.MultivaluedMapImpl;
import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionCommand.Result;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.odataext.odataparser.ODataParser;

/**
 * The Class AuthorizationCommandTest.
 */
public class AuthorizationCommandSelectTest extends AbstractAuthorizationTest {

	/**
	 * Test creation of $select parameter
	 */
	@Test
	public void testSelectCreate() {

		MockAuthorizationBean authBean = new MockAuthorizationBean("", "id");
		AuthorizationCommand command = new AuthorizationCommand(authBean);

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));
		try {
			InteractionCommand.Result result = command.execute(ctx);

			// Should work.
			assertEquals(Result.SUCCESS, result);
		} catch (InteractionException e) {
			// Should never throw.
			fail();
		}
		// Check that the expected parameter is present
		assertEquals("id", ctx.getQueryParameters().getFirst(ODataParser.SELECT_KEY));
	}

	/**
	 * Test removal of a new $select parameter
	 */
	@Test
	public void testSelectRemoveNew() {

		MockAuthorizationBean authBean = new MockAuthorizationBean("", "name, id");
		AuthorizationCommand command = new AuthorizationCommand(authBean);

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();
		queryParams.add(ODataParser.SELECT_KEY, "name");

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));
		try {
			InteractionCommand.Result result = command.execute(ctx);

			// Should work.
			assertEquals(Result.SUCCESS, result);
		} catch (InteractionException e) {
			// Should never throw.
			fail();
		}
		// Check that the expected parameter is present
		// Should just have name left
		assertEquals("name", ctx.getQueryParameters().getFirst(ODataParser.SELECT_KEY));
	}

	/**
	 * Test removal of an existing $select parameter
	 */
	@Test
	public void testSelectRemoveOld() {

		MockAuthorizationBean authBean = new MockAuthorizationBean("", "id");
		AuthorizationCommand command = new AuthorizationCommand(authBean);

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();
		queryParams.add(ODataParser.SELECT_KEY, "name, id");

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));
		try {
			InteractionCommand.Result result = command.execute(ctx);

			// Should work.
			assertEquals(Result.SUCCESS, result);
		} catch (InteractionException e) {
			// Should never throw.
			fail();
		}
		// Check that the expected parameter is present
		// Should just have id left
		assertEquals("id", ctx.getQueryParameters().getFirst(ODataParser.SELECT_KEY));
	}

	/**
	 * Test union of two groups of $select parameters
	 */
	@Test
	public void testSelectUnion() {

		MockAuthorizationBean authBean = new MockAuthorizationBean("", "id, name, street");
		AuthorizationCommand command = new AuthorizationCommand(authBean);

		// Path is not important for security
		MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();

		// Set up oData parameters
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();
		queryParams.add(ODataParser.SELECT_KEY, "id, postcode, name");

		// Run command
		InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
				queryParams, mock(ResourceState.class), mock(Metadata.class));
		try {
			InteractionCommand.Result result = command.execute(ctx);

			// Should work.
			assertEquals(Result.SUCCESS, result);
		} catch (InteractionException e) {
			// Should never throw.
			fail();
		}
		// Check that the expected parameter is present
		// Should just have id and name left
		String result = ctx.getQueryParameters().getFirst(ODataParser.SELECT_KEY);
		assertTrue(result.contains("id"));
		assertTrue(result.contains("name"));
		assertFalse(result.contains("postcode"));
		assertFalse(result.contains("street"));
	}
}
