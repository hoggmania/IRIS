package com.temenos.annotatedtestclasses;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.command.annotation.InteractionCommandImpl;

/**
 * Class to support testing of AnnotationBasedCommandCOntroller
 * This class is annotated with InteractionCommandImpl so this shouldn't be found using reflection
 * because it is not implementing InteractionCommand
 * 
 * @author hmanchala
 */

@InteractionCommandImpl(name = "testName3")
public class AnnotatedInteractionCmdStubImpl3{

    public InteractionCommand.Result execute(InteractionContext ctx) throws InteractionException {
        return InteractionCommand.Result.SUCCESS;
    }
    
}
