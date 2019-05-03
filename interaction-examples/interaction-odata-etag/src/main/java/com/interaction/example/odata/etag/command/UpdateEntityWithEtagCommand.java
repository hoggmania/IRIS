package com.interaction.example.odata.etag.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;

public class UpdateEntityWithEtagCommand implements InteractionCommand {

	private InteractionCommand updateEntityCommand;
	private GETEntityWithEtagCommand getEntityCommand;
	
	public UpdateEntityWithEtagCommand(InteractionCommand updateEntityCommand, GETEntityWithEtagCommand getEntityCommand) {
		this.updateEntityCommand = updateEntityCommand;
		this.getEntityCommand = getEntityCommand;
	}
	
	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		
		//Check if the resource has been modified if an etag has been provided
		InteractionContext getCtx = new InteractionContext(ctx, null, null, null, null);
		getCtx.setResource(null);
		String etag = getEntityCommand.getEtag(getCtx);
		String ifMatch = ctx.getPreconditionIfMatch();
		if(ifMatch != null && !ifMatch.equals(etag)) {
			return Result.CONFLICT;
		}
		return updateEntityCommand.execute(ctx);
	}
}
