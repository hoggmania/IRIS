package com.temenos.interaction.jdbc.exceptions;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

/**
 * Test JdbcException class.
 */
public class TestJdbcException {

	private static Status expectedStatus = Status.FORBIDDEN;
	private static String expectedMessage = "a message";
	private static Exception expectedCause = new Exception("a cause");

	@Test
	public void testConstructorStatus() {

		JdbcException actualException = null;
		try {
			actualException = new JdbcException(expectedStatus);
		} catch (Exception e) {
			fail();
		}

		// Check result
		assertEquals(expectedStatus, actualException.getHttpStatus());
	}

	@Test
	public void testConstructorStatusMessage() {
		JdbcException actualException = null;
		try {
			actualException = new JdbcException(expectedStatus, expectedMessage);
		} catch (Exception e) {
			fail();
		}

		// Check result
		assertEquals(expectedStatus, actualException.getHttpStatus());
		assertEquals(expectedMessage, actualException.getMessage());
	}

	@Test
	public void testConstructorStatusMessageCause() {
		JdbcException actualException = null;
		try {
			actualException = new JdbcException(expectedStatus, expectedMessage, expectedCause);
		} catch (Exception e) {
			fail();
		}

		// Check result
		assertEquals(expectedStatus, actualException.getHttpStatus());
		assertEquals(expectedMessage, actualException.getMessage());
		assertEquals(expectedCause, actualException.getCause());
	}

	@Test
	public void testConstructorStatusCause() {
		JdbcException actualException = null;
		try {
			actualException = new JdbcException(expectedStatus, expectedCause);
		} catch (Exception e) {
			fail();
		}

		// Check result
		assertEquals(expectedStatus, actualException.getHttpStatus());
		assertEquals(expectedCause, actualException.getCause());
	}
}
