package com.temenos.interaction.core.hypermedia.expression;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.resource.EntityResource;


/**
 * Evaluates an {@link Expression} using {@link InteractionContext} and {@link EntityResource}.
 *
 * @author ikarady
 */
public interface ExpressionEvaluator {

    /**
     * Evaluate an {@link Expression} using {@link InteractionContext} and {@link EntityResource}.
     * Return a boolean result.
     *
     * @param expression the {@link Expression} to evaluate
     * @param ctx the {@link InteractionContext} used for evaluation
     * @param resource the {@link EntityResource} used for evaluation

     * @return true or false
     */
    public boolean evaluate(Expression expression, InteractionContext ctx, EntityResource<?> resource);

}
