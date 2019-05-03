package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.resource.RESTResource;

public 	class NoopPOSTCommand implements InteractionCommand {

	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) {
	    assert(ctx != null);
        RESTResource resource = ctx.getResource();
        if(resource != null) {
            ctx.setResource(resource);
        }
        else {
            ctx.setResource(new EntityResource<Object>());
        }
        
        ctx.getOutQueryParameters().putAll(ctx.getQueryParameters());
		return Result.CREATED;
	}

};
