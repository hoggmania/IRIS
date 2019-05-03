package com.temenos.interaction.example.hateoas.simple;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.example.hateoas.simple.model.Note;

public class CREATENoteCommand implements InteractionCommand {

	private Persistence persistence;

	public CREATENoteCommand(Persistence p) {
		this.persistence = p;
	}
	
	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) {
		assert(ctx != null);
		// insert note into the database, etc.
		persistence.insertNote((Note) ctx.getResource());
		return Result.SUCCESS;
	}

}
