package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.TransitionCommand;


/**
 * An {@link InteractionCommand} that orchestrates a workflow
 * of command execution.
 *
 * @author ikarady
 */
public interface WorkflowCommand extends InteractionCommand {

    /**
     * Type of execution performed.
     */
    enum ExecutionType {
        /**
         * Represents the case when the last executed {@link InteractionCommand}
         * was not a {@link TransitionCommand}.
         */
        INTERACTION,
        /**
         * Represents the case when the last executed {@link InteractionCommand}
         * was a {@link TransitionCommand}.
         */
        TRANSITION
    }

    /**
     * Returns true if there are no {@link InteractionCommand}s in workflow.
     *
     * @return true or false
     */
    public boolean isEmpty();

    /**
     * Returns the type of execution performed depending on
     * whether the last {@link InteractionCommand} executed
     * was a {@link TransitionCommand}.
     *
     * @return {@link ExecutionType} the type of execution result.
     */
    public ExecutionType getExecutionType();

}
