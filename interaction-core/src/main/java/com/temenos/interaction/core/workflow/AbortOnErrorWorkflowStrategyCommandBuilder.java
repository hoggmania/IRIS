package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.CommandController;
import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.hypermedia.Action;
import com.temenos.interaction.core.workflow.AbortOnErrorWorkflowStrategyCommand;
import com.temenos.interaction.core.workflow.WorkflowCommandBuilder;

import java.util.List;


/**
 * An {@link AbortOnErrorWorkflowStrategyCommand} builder that
 * uses a {@link CommandController} to create {@link InteractionCommand}s
 * out of a list of {@link Action}s and feed them into the
 * {@link AbortOnErrorWorkflowStrategyCommand} it builds.
 *
 * @author ikarady
 */
public class AbortOnErrorWorkflowStrategyCommandBuilder implements WorkflowCommandBuilder {

    private CommandController commandController;

    public AbortOnErrorWorkflowStrategyCommandBuilder(CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Builds an {@link AbortOnErrorWorkflowStrategyCommand} from
     * {@link InteractionCommand}s created from the list of {@link Action}s.
     *
     * @param actions   list of {@link Action}s
     *
     * @return {@link AbortOnErrorWorkflowStrategyCommand}
     */
    @Override
    public AbortOnErrorWorkflowStrategyCommand build(List<Action> actions) {
        AbortOnErrorWorkflowStrategyCommand workflow = new AbortOnErrorWorkflowStrategyCommand();
        for (Action action : actions) {
            assert action != null;
            workflow.addCommand(commandController.fetchCommand(action.getName()));
        }
        return workflow;
    }

    /**
     * Builds a {@link AbortOnErrorWorkflowStrategyCommand} from
     * a list of {@link InteractionCommand}s.
     *
     * @param commands  list of {@link InteractionCommand}s
     *
     * @return {@link AbortOnErrorWorkflowStrategyCommand}
     */
    @Override
    public WorkflowCommand build(InteractionCommand[] commands) {
        AbortOnErrorWorkflowStrategyCommand workflow = new AbortOnErrorWorkflowStrategyCommand();
        for (InteractionCommand command : commands) {
            if (command != null) {
                workflow.addCommand(command);
            }
        }
        return workflow;
    }

}
