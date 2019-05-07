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
 * This class is implementing InteractionCommand and is annotated with InteractionCommandImpl
 * 
 * @author hmanchala
 */

@InteractionCommandImpl(name = "testName2")
public class AnnotatedInteractionCmdStubImpl2 implements InteractionCommand{

    public InteractionCommand.Result execute(InteractionContext ctx) throws InteractionException {
        return InteractionCommand.Result.SUCCESS;
    }
    
}
