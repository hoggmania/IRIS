package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.List;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.command.TransitionCommand;


/**
 * This command implements a naive workflow where all commands are executed.
 * Commands are added to this workflow and then executed in the same order regardless
 * of their return code {@link InteractionCommand.Result.SUCCESS} or otherwise.
 * @author aphethean
 */
public class NaiveWorkflowStrategyCommand implements WorkflowCommand {

	protected List<InteractionCommand> commands = new ArrayList<InteractionCommand>();
	protected InteractionCommand lastExecutedCommand = null;

	public NaiveWorkflowStrategyCommand() {}
	
	/**
	 * Construct with a list of commands to execute.
	 * @param commands
	 * @invariant commands not null
	 */
	public NaiveWorkflowStrategyCommand(List<InteractionCommand> commands) {
		this.commands = commands;
		if (commands == null)
			throw new IllegalArgumentException("No commands supplied");		
	}

	public void addCommand(InteractionCommand command) {
		if (command == null)
			throw new IllegalArgumentException("No command supplied");		
		commands.add(command);
	}

	@Override
	public boolean isEmpty() {
		return commands.isEmpty();
	}

	@Override
	public ExecutionType getExecutionType() {
		if (lastExecutedCommand instanceof WorkflowCommand) {
			return ((WorkflowCommand)lastExecutedCommand).getExecutionType();
		}
		return lastExecutedCommand instanceof TransitionCommand ? ExecutionType.TRANSITION : ExecutionType.INTERACTION;
	}

	/**
	 * @throws InteractionException 
	 * @precondition at least one command has been added {@link addCommand}
	 * @postcondition returned {@link Result) will always be the result
	 * of the last command.
	 */
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(commands != null);
		assert(commands.size() > 0) : "There must be at least one command in the workflow";
		if (ctx == null)
			throw new IllegalArgumentException("InteractionContext must be supplied");

		Result result = null;
		for (InteractionCommand command : commands) {
			lastExecutedCommand = command;
			result = command.execute(ctx);
		}
		return result;
	}

}
