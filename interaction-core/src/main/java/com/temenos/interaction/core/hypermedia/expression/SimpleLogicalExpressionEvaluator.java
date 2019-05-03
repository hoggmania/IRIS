package com.temenos.interaction.core.hypermedia.expression;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.hypermedia.Transition;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.rim.HTTPHypermediaRIM;

/**
 * A very simple Expression implementation that supports left to right, 'AND' expression
 * evaluation (short-circuiting logical expressions with just the logical AND operator)
 * @author aphethean
 */
public class SimpleLogicalExpressionEvaluator implements Expression {

	private final List<Expression> expressions;
	private final Set<Transition> transitions = new HashSet<Transition>();
	
	public SimpleLogicalExpressionEvaluator(List<Expression> expressions) {
		this.expressions = expressions;
		assert(this.expressions != null);
		for (Expression e : expressions) {
			transitions.addAll(e.getTransitions());
		}
	}
	
	@Override
	public boolean evaluate(HTTPHypermediaRIM rimHandler, InteractionContext ctx, EntityResource<?> resource) {
		for (Expression e : expressions) {
			if (!e.evaluate(rimHandler, ctx, resource))
				return false;
		}
		return true;
	}
	
	@Override
	public Set<Transition> getTransitions() {
		return transitions;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Expression e : expressions)
			sb.append(e);		
		return sb.toString();
	}
}
