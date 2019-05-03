package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.command.CommandController;

import java.util.EnumMap;
import java.util.Map;


/**
 * A factory to create a {@link WorkflowCommandBuilder}.
 *
 * @author ikarady
 */
public class WorkflowCommandBuilderFactory implements WorkflowCommandBuilderProvider {

    private Map<WorkflowType, WorkflowCommandBuilder> builderMap;

    public WorkflowCommandBuilderFactory(CommandController commandController) {
        builderMap = getDefaultBuilderMap(commandController);
    }

    public void setBuilderMap(Map<WorkflowType, WorkflowCommandBuilder> builderMap) {
        this.builderMap = builderMap;
    }

    @Override
    public WorkflowCommandBuilder getBuilder(WorkflowType workflowType) {
        return builderMap.get(workflowType);
    }

    private static Map<WorkflowType, WorkflowCommandBuilder> getDefaultBuilderMap(CommandController commandController) {
        Map<WorkflowType, WorkflowCommandBuilder> defaultBuilderMap = new EnumMap<>(WorkflowType.class);
        defaultBuilderMap.put(WorkflowType.INTERACTION, new InteractionWorkflowStrategyCommandBuilder(commandController));
        defaultBuilderMap.put(WorkflowType.TRANSITION, new TransitionWorkflowStrategyCommandBuilder(commandController));
        return defaultBuilderMap;
    }

}
