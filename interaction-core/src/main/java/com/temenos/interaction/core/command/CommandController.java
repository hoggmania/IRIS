package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 * Interface of registry of {@link InteractionCommand}, returning an instance for a given name.
 * Abstracting the command registry initialisation and command resolution logic from the upper layers.
 * @author pblair
 * @author trojanbug
 */
public interface CommandController {

    /**
     * 
     * @param name The name of the command as declared in RIM, derived from the request
     * @return Instance of {@link InteractionCommand} corresponding to name argument, or null, if not found. Implementations are discouraged from throwing exceptions if the name cannot be found, to support composition.
     */
    public InteractionCommand fetchCommand(String name);
    public boolean isValidCommand(String name);
}