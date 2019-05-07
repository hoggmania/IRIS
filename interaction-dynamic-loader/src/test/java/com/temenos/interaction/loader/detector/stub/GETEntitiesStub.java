/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temenos.interaction.loader.detector.stub;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.command.InteractionCommand;
import static com.temenos.interaction.core.command.InteractionCommand.Result.SUCCESS;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;

/**
 *
 * @author andres
 */
public class GETEntitiesStub implements InteractionCommand {

    public Result execute(InteractionContext ctx) throws InteractionException {       
        
        return SUCCESS;
    }
 
}
