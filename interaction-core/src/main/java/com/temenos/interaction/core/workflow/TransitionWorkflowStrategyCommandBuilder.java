package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.CommandController;
import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.TransitionCommand;
import com.temenos.interaction.core.hypermedia.Action;

import java.util.List;


/**
 * An {@link TransitionWorkflowStrategyCommand} builder that
 * uses a {@link CommandController} to create {@link InteractionCommand}s
 * out of a list of {@link Action}s and feed them into the
 * {@link TransitionWorkflowStrategyCommand} it builds.
 *
 * @author ikarady
 */
public class TransitionWorkflowStrategyCommandBuilder implements WorkflowCommandBuilder {

    private CommandController commandController;

    public TransitionWorkflowStrategyCommandBuilder(CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Builds a {@link TransitionWorkflowStrategyCommand} from
     * {@link InteractionCommand}s created from the list of {@link Action}s.
     *
     * @param actions   list of {@link Action}s
     *
     * @return {@link TransitionWorkflowStrategyCommand}
     */
    @Override
    public WorkflowCommand build(List<Action> actions) {
        TransitionWorkflowStrategyCommand workflow = new TransitionWorkflowStrategyCommand();
        for (Action action : actions) {
            assert action != null;
            workflow.addCommand(commandController.fetchCommand(action.getName()));
        }
        return workflow;
    }

    /**
     * Builds a {@link TransitionWorkflowStrategyCommand} from
     * a list of {@link TransitionCommand}s.
     *
     * @param commands  list of {@link TransitionCommand}s
     *
     * @return {@link TransitionWorkflowStrategyCommand}
     */
    @Override
    public WorkflowCommand build(InteractionCommand[] commands) {
        TransitionWorkflowStrategyCommand workflow = new TransitionWorkflowStrategyCommand();
        for (InteractionCommand command : commands) {
            if (command != null) {
                workflow.addCommand(command);
            }
        }
        return workflow;
    }

}
