package com.temenos.interaction.core.command;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Collections;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.temenos.interaction.core.MultivaluedMapImpl;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.Action;
import com.temenos.interaction.core.hypermedia.ResourceState;

public class TestMatchCommand {
	private InteractionCommand cmd;
	private InteractionContext ctx;
	private Properties commandProperties;
	
	protected void setExpression(String expression) {
		commandProperties.setProperty("Expression", expression);
	}
	
	@Before
	public void setup() {
		MultivaluedMapImpl pParams = new MultivaluedMapImpl();
		MultivaluedMapImpl qParams = new MultivaluedMapImpl();

		pParams.add("Variable", "Value");
		qParams.add("Query", "Apple");
		pParams.add("Variable2", "Value2");
        qParams.add("Query2", "Orange");

		cmd = new MatchCommand();
		commandProperties = new Properties();
		Action getAction = new Action("GET", Action.TYPE.VIEW, commandProperties);
		ResourceState currentState = new ResourceState("Entity", "match", Collections.singletonList(getAction), "/");
		ctx = new InteractionContext(null,
									 null,
									 pParams,
									 qParams,
									 currentState,
									 new Metadata("Test"));
	}
	
	@Test
	public void testEquals() throws InteractionException {
		setExpression("{Variable}='Value'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testEqualsFail() throws InteractionException {
		setExpression("{Variable}='Vaalue'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.FAILURE, result);
	}

	@Test
	public void testQuoting() throws InteractionException {
		setExpression("Thing='Thing'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testQuotingDeref() throws InteractionException {
		setExpression("'{Variable}'='Value'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.FAILURE, result);
	}

	@Test
	public void testQuotingBrackets() throws InteractionException {
		setExpression("'{Variable}'='{Variable}'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testQueryParam() throws InteractionException {
		setExpression("{Query}='Apple'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	/* A variable that does not exist resolves to its name */
	@Test
	public void testMissingVar() throws InteractionException {
		setExpression("{Undefined}='Undefined'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testSpaces() throws InteractionException {
		setExpression("{Variable} = 'Value'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testNotEquals() throws InteractionException {
		setExpression("{Variable}!='Wrong'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testNotEqualsFail() throws InteractionException {
		setExpression("{Variable}!='Value'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.FAILURE, result);
	}

	@Test
	public void testStartsWith() throws InteractionException {
		setExpression("{Variable}startsWith'Val'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

    @Test
    public void testEqualsVariableExpression2() throws InteractionException {
        setExpression("{Variable}='Value2' | {Variable2}='Value2'");
        InteractionCommand.Result result = cmd.execute(ctx);
        assertEquals(InteractionCommand.Result.SUCCESS, result);
    }

    @Test
    public void testEqualsVariableExpression2Fail() throws InteractionException {
        setExpression("{Variable}='Value2' | {Variable}='Value2'");
        InteractionCommand.Result result = cmd.execute(ctx);
        assertEquals(InteractionCommand.Result.FAILURE, result);
    }

    @Test
    public void testEqualsQueryExpression2() throws InteractionException {
        setExpression("{Query}='Orange'   |   {Query2}='Orange'");
        InteractionCommand.Result result = cmd.execute(ctx);
        assertEquals(InteractionCommand.Result.SUCCESS, result);
    }

    @Test
    public void testEqualsQueryExpression2Fail() throws InteractionException {
        setExpression("{Query}='Orange' | {Query}='Orange'");
        InteractionCommand.Result result = cmd.execute(ctx);
        assertEquals(InteractionCommand.Result.FAILURE, result);
    }

    @Test
    public void testExpressionsSpaceFail() throws InteractionException {
        setExpression("{Query}='Orange' |{Query2}='Orange'");
        InteractionCommand.Result result = cmd.execute(ctx);
        assertEquals(InteractionCommand.Result.FAILURE, result);
    }

    @Test
    public void testExpressionsFail() throws InteractionException {
        setExpression("{Var}='Unresolved' | {Var2}='Unresolved1'");
        InteractionCommand.Result result = cmd.execute(ctx);
        assertEquals(InteractionCommand.Result.FAILURE, result);
    }

	@Test
	public void testStartsWithLiteral() throws InteractionException {
		setExpression("'Value' startsWith 'Val'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}

	@Test
	public void testStartsWithSpace() throws InteractionException {
		setExpression("{Variable} startsWith 'Val'");
		InteractionCommand.Result result = cmd.execute(ctx);
		assertEquals(InteractionCommand.Result.SUCCESS, result);
	}
}
