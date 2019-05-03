package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.hypermedia.Transition;


/**
 * An interim {@link TransitionCommand} that represents a {@link Transition}
 * that can only be successful when one of the {@link Transition}s
 * of its target {@link ResourceState} is successful.
 *
 * @author ikarady
 */
public class InterimTransitionCommand implements TransitionCommand {

    @Override
    public Result execute(InteractionContext ctx) throws InteractionException {
        return Result.SUCCESS;
    }

    @Override
    public boolean isInterim() {
        return true;
    }
}
