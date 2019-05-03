package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.TransitionCommand;

import java.util.ArrayList;
import java.util.List;


/**
 * An implementation of a workflow {@link InteractionCommand} that only uses
 * commands that are not of type {@link TransitionCommand}.
 *
 * @author ikarady
 */
public class InteractionWorkflowStrategyCommand extends AbortOnErrorWorkflowStrategyCommand {

    public InteractionWorkflowStrategyCommand() {}

    /**
     * Construct with a list of commands to execute.
     * @param commands
     * @invariant commands not null
     */
    public InteractionWorkflowStrategyCommand(List<InteractionCommand> commands) {
        super(commands);
    }

    @Override
    public void addCommand(InteractionCommand command) {
        if (command instanceof TransitionCommand) {
            return;
        }
        super.addCommand(command);
    }

}
