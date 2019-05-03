package com.temenos.interaction.core.hypermedia.expression;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Set;

import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.hypermedia.Transition;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.rim.HTTPHypermediaRIM;

public interface Expression {

	/**
	 * Evaluate this expression and return a boolean result.
	 * @param pathParams
	 * @return
	 */
	public boolean evaluate(HTTPHypermediaRIM rimHandler, InteractionContext ctx, EntityResource<?> resource);
	
	public Set<Transition> getTransitions();
}
