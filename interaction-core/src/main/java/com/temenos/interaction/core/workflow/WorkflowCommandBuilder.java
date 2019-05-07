package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.TransitionCommand;
import com.temenos.interaction.core.hypermedia.Action;

import java.util.List;


/**
 * A builder to create a {@link WorkflowCommand}
 * out of a list of {@link Action}s or a list of {@link InteractionCommand}s.
 *
 * @author ikarady
 */
public interface WorkflowCommandBuilder {

    /**
     * Builds a {@link WorkflowCommand} from
     * {@link InteractionCommand}s created from the list of {@link Action}s.
     *
     * @param actions   list of {@link Action}s
     *
     * @return {@link WorkflowCommand}
     */
    WorkflowCommand build(List<Action> actions);

    /**
     * Builds a {@link WorkflowCommand} out of a list of {@link InteractionCommand}s.
     *
     * @param commands  list of {@link InteractionCommand}s
     *
     * @return  {@link WorkflowCommand}
     */
    WorkflowCommand build(InteractionCommand[] commands);
}
