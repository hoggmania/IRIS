package com.temenos.interaction.example.hateoas.dynamic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.Response.Status;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.example.hateoas.dynamic.model.Author;

public class GETAuthorCommand implements InteractionCommand {

	private Persistence persistence;
	
	public GETAuthorCommand(Persistence p) {
		persistence = p;
	}

	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		// retrieve from a database, etc.
		String id = ctx.getId();
		Author author = persistence.getAuthor(id);
		if (author != null) {
			ctx.setResource(new EntityResource<Author>(author));
			return Result.SUCCESS;
		} else {
			throw new InteractionException(Status.NOT_FOUND);
		}
	}

}
