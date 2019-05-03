package com.temenos.interaction.core.workflow;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.Response.Status.Family;

import com.temenos.interaction.core.command.TransitionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;

/**
 * <p>This command implements a workflow that will retry if there is an error.</p>
 * Commands are added to this workflow and then retried according to the retry count
 * and the incremental backoff (configured in milliseconds).
 * @author aphethean
 */
public class RetryWorkflowStrategyCommand implements WorkflowCommand {
	private final static Logger logger = LoggerFactory.getLogger(RetryWorkflowStrategyCommand.class);
	private InteractionCommand command;
	private int maxRetryCount;
	private long maxRetryInterval;
	protected InteractionCommand lastExecutedCommand;

	/**
	 * Construct with a list of commands to execute.
	 * @param commands
	 * @param maxRetryCount
	 * @param maxRetryInterval (in milliseconds)
	 * @invariant commands not null
	 */
	public RetryWorkflowStrategyCommand(InteractionCommand command, int maxRetryCount, long maxRetryInterval) {
		this.command = command;
		this.maxRetryCount = maxRetryCount;
		this.maxRetryInterval = maxRetryInterval;
		if (command == null)
			throw new IllegalArgumentException("No commands supplied");		
	}

	/**
	 * @throws InteractionException 
	 * if Family.SERVER_ERROR error then retry maxRetryCount times
	 * and keep incremental interval according to maxRetryInterval 
	 */
	private Result commandExecute(InteractionContext ctx) throws InteractionException {
		Result result = null;
		int retryCount = -1;
		while ( ( maxRetryCount - retryCount++ ) > -1 ) {
			try {
				lastExecutedCommand = command;
				result = command.execute(ctx);
				break;
			} catch (InteractionException ex) {
				if (Family.SERVER_ERROR.equals(ex.getHttpStatus().getFamily()) && retryCount < maxRetryCount ) {
					long nextRetry = maxRetryInterval * (int)Math.pow(2,retryCount);
						logger.info("iris_request maxRetryCount=" + String.valueOf(maxRetryCount) +
								" maxRetryInterval=" + String.valueOf(maxRetryInterval) +
								" retryingNumber=" + String.valueOf(retryCount) +
								" nextRetryIn=" + String.valueOf(nextRetry) + " seconds");
					try {
						Thread.sleep(nextRetry);
					} catch (InterruptedException e) {
						logger.error("InterruptedException: ", e);
					}
				} else { throw ex; }
			}
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return command == null;
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
	 */
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(command != null);
		if (ctx == null)
			throw new IllegalArgumentException("InteractionContext must be supplied");

		return commandExecute(ctx);
	}
}
