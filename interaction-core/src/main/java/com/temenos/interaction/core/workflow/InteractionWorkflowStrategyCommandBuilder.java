package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.CommandController;
import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.hypermedia.Action;

import java.util.List;


/**
 * An {@link InteractionWorkflowStrategyCommand} builder that
 * uses a {@link CommandController} to create {@link InteractionCommand}s
 * out of a list of {@link Action}s and feed them into the
 * {@link InteractionWorkflowStrategyCommand} it builds.
 *
 * @author ikarady
 */
public class InteractionWorkflowStrategyCommandBuilder implements WorkflowCommandBuilder {

    private CommandController commandController;

    public InteractionWorkflowStrategyCommandBuilder(CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Builds an {@link InteractionWorkflowStrategyCommand} from
     * {@link InteractionCommand}s created from the list of {@link Action}s.
     *
     * @param actions   list of {@link Action}s
     *
     * @return {@link InteractionWorkflowStrategyCommand}
     */
    @Override
    public InteractionWorkflowStrategyCommand build(List<Action> actions) {
        InteractionWorkflowStrategyCommand workflow = new InteractionWorkflowStrategyCommand();
        for (Action action : actions) {
            assert action != null;
            workflow.addCommand(commandController.fetchCommand(action.getName()));
        }
        return workflow;
    }

    /**
     * Builds a {@link InteractionWorkflowStrategyCommand} from
     * a list of {@link InteractionCommand}s.
     *
     * @param commands  list of {@link InteractionCommand}s
     *
     * @return {@link InteractionWorkflowStrategyCommand}
     */
    @Override
    public WorkflowCommand build(InteractionCommand[] commands) {
        InteractionWorkflowStrategyCommand workflow = new InteractionWorkflowStrategyCommand();
        for (InteractionCommand command : commands) {
            if (command != null) {
                workflow.addCommand(command);
            }
        }
        return workflow;
    }

}
