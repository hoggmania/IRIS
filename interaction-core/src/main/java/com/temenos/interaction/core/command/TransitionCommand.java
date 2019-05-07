package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.hypermedia.Transition;


/**
 * An {@link InteractionCommand} that does not make service calls
 * but performs a {@link Transition} on an {@link InteractionContext}.
 *
 * @author ikarady
 */
public interface TransitionCommand extends InteractionCommand {

    /**
     * Returns true if this {@link TransitionCommand} is interim otherwise false.
     * A {@link TransitionCommand} is interim if the {@link Transition}
     * it represents can only be successful when one of the {@link Transition}s
     * of its target {@link ResourceState} is successful.
     *
     * @return true or false
     */
    public boolean isInterim();
}
