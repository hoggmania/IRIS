package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Implementors of this interface are providing commands that will be
 * executed when a given interaction with a resource occurs.
 * @author aphethean
 */
public interface InteractionCommand {

	enum Result {
		SUCCESS, 
		FAILURE, 
		INVALID_REQUEST, 
		CONFLICT,
		CREATED
	}
	
	/**
	 * Main execution interface for resource interactions.
	 * @precondition a valid, non null {@link InteractionContext}
	 * @postcondition a non null InteractionCommand.Result indicating command outcome
	 * @param ctx
	 * @throws interaction command exception
	 * @return result
	 */
	public Result execute(InteractionContext ctx) throws InteractionException;
	
}
