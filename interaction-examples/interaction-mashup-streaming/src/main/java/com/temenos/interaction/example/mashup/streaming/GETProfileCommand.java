package com.temenos.interaction.example.mashup.streaming;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.web.RequestContext;
import com.temenos.interaction.example.mashup.streaming.model.Profile;

public class GETProfileCommand implements InteractionCommand {
    private final static Logger logger = LoggerFactory.getLogger(Persistence.class);

	private Persistence persistence;
	
	public GETProfileCommand(Persistence p) {
		persistence = p;
	}

	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		Profile profile = getProfile(ctx);
		if (profile != null) {
			ctx.setResource(new EntityResource<Profile>(profile));
			return Result.SUCCESS;
		} else {
			return Result.FAILURE;
		}
	}

	public Profile getProfile(InteractionContext ctx) {
		// retrieve from a database for the signed in user.
		RequestContext requestContext = RequestContext.getRequestContext();
		String id = (requestContext.getUserPrincipal() != null ? requestContext.getUserPrincipal().getName() : null);
		if (id == null) {
			id = ctx.getQueryParameters().getFirst("userid");
		}
		// use test default
		if (id == null) {
			id = "someone";
		}
		logger.info("Getting Profile for ["+id+"]");
		Profile profile = persistence.getProfile(id);
		return profile;
	}
}
