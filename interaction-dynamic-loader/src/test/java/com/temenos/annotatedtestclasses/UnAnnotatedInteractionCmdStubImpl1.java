package com.temenos.annotatedtestclasses;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;

/**
 * Class to support testing of AnnotationBasedCommandCOntroller
 * This class shouldn't be found using reflection
 * because it is not implementing InteractionCommand & annotated with InteractionCommandImpl
 * 
 * @author hmanchala
 */
public class UnAnnotatedInteractionCmdStubImpl1 implements InteractionCommand{

    public Result execute(InteractionContext ctx) throws InteractionException {
        return Result.SUCCESS;
    }
    
}
