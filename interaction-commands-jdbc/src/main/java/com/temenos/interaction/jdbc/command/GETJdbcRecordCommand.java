package com.temenos.interaction.jdbc.command;

/*
 * Jdbc command. 
 * 
 * Given an enquiry constructs a Jdbc request and recovers the requested information from a JDBCProducer.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.authorization.command.AuthorizationAttributes;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.entity.Entity;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.jdbc.exceptions.JdbcException;
import com.temenos.interaction.jdbc.producer.JdbcProducer;

public class GETJdbcRecordCommand implements JdbcCommand {
	private final static Logger logger = LoggerFactory.getLogger(GETJdbcRecordCommand.class);

	// Somewhere to store the producer.
	JdbcProducer producer;

	public GETJdbcRecordCommand(JdbcProducer producer) {
		this.producer = producer;
	}

	/*
	 * Execute the command.
	 * 
	 * If there is any form of internal error during authorization this will
	 * throw and nothing should be returned to the use.
	 */
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {

		// Unpack interaction context parameters,
		String entityType = ctx.getCurrentState().getEntityName();

		// For raw command table name is the same as the entity type.
		String tableName = entityType;

		String key = ctx.getId();

		// Get data from JDBC
		EntityResource<Entity> result = null;
		try {
			result = producer.queryEntity(tableName, key, ctx, entityType);
		} catch (JdbcException e) {
			logger.error("Jdbc query failed. " + e);

			if (Status.NOT_FOUND == e.getHttpStatus()) {
				// Not found is just a failure.
				return Result.FAILURE;
			}

			// Other Interaction exceptions should thrown up to the framework.
			throw new InteractionException(e.getHttpStatus(), e);
		} catch (Exception e) {
			logger.error("Jdbc query failed with internal error. " + e);

			// Wrap other exception in an InteractionException and throw to
			// framework.
			throw (new InteractionException(Status.INTERNAL_SERVER_ERROR, e));
		}

		// Write result into context
		ctx.setResource(result);

		// Indicate that database level filtering was successful.
		ctx.setAttribute(AuthorizationAttributes.FILTER_DONE_ATTRIBUTE, Boolean.TRUE);
		ctx.setAttribute(AuthorizationAttributes.SELECT_DONE_ATTRIBUTE, Boolean.TRUE);

		return Result.SUCCESS;

	}

}
