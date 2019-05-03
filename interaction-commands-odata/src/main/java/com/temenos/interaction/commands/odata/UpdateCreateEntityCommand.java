package com.temenos.interaction.commands.odata;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.Response.Status;

import org.odata4j.core.OEntity;
import org.odata4j.core.OEntityKey;
import org.odata4j.edm.EdmEntitySet;
import org.odata4j.exceptions.NotFoundException;
import org.odata4j.exceptions.ODataProducerException;
import org.odata4j.producer.ODataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.entity.Entity;

/**
 * This command attempts to update an entity, it will create the entity if it doesn't exist.
 * @author aphethean
 *
 */
public class UpdateCreateEntityCommand extends AbstractODataCommand implements InteractionCommand {
	private final static Logger LOGGER = LoggerFactory.getLogger(UpdateCreateEntityCommand.class);

	public UpdateCreateEntityCommand(ODataProducer producer) {
		super(producer);
	}

	/* Implement InteractionCommand interface */
	
	@SuppressWarnings("unchecked")
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		assert(ctx.getCurrentState() != null);
		assert(ctx.getCurrentState().getEntityName() != null && !ctx.getCurrentState().getEntityName().equals(""));
		assert(ctx.getResource() != null);
		
		Result result = null;
		
		// update the entity
		String entityName = getEntityName(ctx);
		LOGGER.debug("Getting entity for " + entityName);
		try {
			EdmEntitySet entitySet = getEdmEntitySet(entityName);
			// create the entity
			OEntity entity = null;
			try {
				entity = ((EntityResource<OEntity>) ctx.getResource()).getEntity();
			} catch (ClassCastException cce) {
			    if(LOGGER.isDebugEnabled()) {
			        LOGGER.debug("OEntity class not found.", cce);
			    }
				//Create entity key (simple types only)
				OEntityKey key = CommandHelper.createEntityKey(entitySet, ctx.getId());
				entity = CommandHelper.createOEntityFromEntity(this, producer, ((EntityResource<Entity>) ctx.getResource()).getEntity(), key);
			}

			if (merge(entityName, entity)) {
				result = Result.SUCCESS;
			} else {
				producer.createEntity(entitySet.getName(), entity);
				result = Result.CREATED;
			}
		} catch(ODataProducerException ope) {
			LOGGER.debug("Failed to update entity [" + entityName + "]: ", ope);
			throw new InteractionException(ope.getHttpStatus(), ope);
		} catch(Exception e) {
			LOGGER.debug("Error while updating entity [" + entityName + "]: ", e);
			throw new InteractionException(Status.INTERNAL_SERVER_ERROR, e);
		}
		
		assert(result != null);
		ctx.setResource(null);
		return result;
	}
	
	private boolean merge(String entityName, OEntity entity) throws ODataProducerException {
		try {
			producer.mergeEntity(entityName, entity);
		} catch(NotFoundException nfe) {
			LOGGER.debug("Failed to update entity, not found [" + entityName + "]: ", nfe);
			return false;
		}
		return true;
	}

}
