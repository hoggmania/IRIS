package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * A provider for {@link WorkflowCommandBuilder}.
 *
 * @author ikarady
 */
public interface WorkflowCommandBuilderProvider {

    /**
     * Type of workflow for which {@link WorkflowCommandBuilder} is provided.
     *
     * @author ikarady
     */
    public enum WorkflowType {
        INTERACTION, TRANSITION;
    }

    /**
     * Returns a {@link WorkflowCommandBuilder} for given {@link WorkflowType}.
     *
     * @return {@link WorkflowCommandBuilder}
     *
     * @author ikarady
     */
    public WorkflowCommandBuilder getBuilder(WorkflowType workflowType);

}
