package com.interaction.example.odata.error.command;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 * Use this class to simulate IRIS behaviour if a
 * RuntimeException is thrown.
 *
 * @author dgroves
 *
 */
public class ThrowARuntimeExceptionCommand implements InteractionCommand {

    @Override
    public Result execute(InteractionContext ctx) throws InteractionException, RuntimeException {
        throw new RuntimeException(ctx.getCurrentState().getName() + " is a Runtime Exception.");
    }

}
