package com.temenos.interaction.core.hypermedia.expression;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.rim.HTTPHypermediaRIM;

public class TestSimpleLogicalExpressionEvaluator {

	private Expression createFalseExpression() {
		Expression expression = mock(Expression.class);
		when(expression.evaluate(any(HTTPHypermediaRIM.class), any(InteractionContext.class), any(EntityResource.class))).thenReturn(false);
		return expression;
	}

	private Expression createTrueExpression() {
		Expression expression = mock(Expression.class);
		when(expression.evaluate(any(HTTPHypermediaRIM.class), any(InteractionContext.class), any(EntityResource.class))).thenReturn(true);
		return expression;
	}

	@Test
	public void testNoExpressions() {
		List<Expression> expressions = new ArrayList<Expression>();
		SimpleLogicalExpressionEvaluator expEvaluator = new SimpleLogicalExpressionEvaluator(expressions);

		assertTrue(expEvaluator.evaluate(mock(HTTPHypermediaRIM.class), mock(InteractionContext.class), null));
	}

	@Test
	public void testOneTrueExpression() {
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(createTrueExpression());
		SimpleLogicalExpressionEvaluator expEvaluator = new SimpleLogicalExpressionEvaluator(expressions);

		assertTrue(expEvaluator.evaluate(mock(HTTPHypermediaRIM.class), mock(InteractionContext.class), null));
	}
	
	@Test
	public void testOneFalseExpression() {
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(createFalseExpression());
		SimpleLogicalExpressionEvaluator expEvaluator = new SimpleLogicalExpressionEvaluator(expressions);

		assertFalse(expEvaluator.evaluate(mock(HTTPHypermediaRIM.class), mock(InteractionContext.class), null));
	}

	@Test
	public void testTrueFalseExpression() {
		Expression trueEX = createTrueExpression();
		Expression falseEX = createFalseExpression();

		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(trueEX);
		expressions.add(falseEX);
		SimpleLogicalExpressionEvaluator expEvaluator = new SimpleLogicalExpressionEvaluator(expressions);

		assertFalse(expEvaluator.evaluate(mock(HTTPHypermediaRIM.class), mock(InteractionContext.class), null));
		verify(trueEX).evaluate(any(HTTPHypermediaRIM.class), any(InteractionContext.class), any(EntityResource.class));
		verify(falseEX).evaluate(any(HTTPHypermediaRIM.class), any(InteractionContext.class), any(EntityResource.class));
	}

	@Test
	public void testFalseTrueExpression() {
		Expression falseEX = createFalseExpression();
		Expression trueEX = createTrueExpression();
		
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(falseEX);
		expressions.add(trueEX);
		SimpleLogicalExpressionEvaluator expEvaluator = new SimpleLogicalExpressionEvaluator(expressions);

		assertFalse(expEvaluator.evaluate(mock(HTTPHypermediaRIM.class), mock(InteractionContext.class), null));
		verify(falseEX).evaluate(any(HTTPHypermediaRIM.class), any(InteractionContext.class), any(EntityResource.class));
		verify(trueEX, never()).evaluate(any(HTTPHypermediaRIM.class), any(InteractionContext.class), any(EntityResource.class));
	}

}
