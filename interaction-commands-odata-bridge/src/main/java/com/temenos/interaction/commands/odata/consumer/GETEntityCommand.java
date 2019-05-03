package com.temenos.interaction.commands.odata.consumer;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.odata4j.consumer.ODataConsumer;
import org.odata4j.core.OEntity;
import org.odata4j.core.OEntityGetRequest;
import org.odata4j.core.OEntityKey;
import org.odata4j.edm.EdmDataServices;
import org.odata4j.edm.EdmEntitySet;
import org.odata4j.edm.EdmEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.resource.EntityResource;

public class GETEntityCommand implements InteractionCommand {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GETEntityCommand.class);

	private ODataConsumer consumer;
	private EdmDataServices edmDataServices;

	public GETEntityCommand(ODataConsumer consumer) {
		this.consumer = consumer;
		this.edmDataServices = consumer.getMetadata();
	}
	
	/* Implement ResourceGetCommand (OEntity) */
	@Override
	public Result execute(InteractionContext ctx) {
		assert ctx != null;
		assert ctx.getCurrentState() != null;
		assert ctx.getCurrentState().getEntityName() != null && !"".equals(ctx.getCurrentState().getEntityName());
		assert ctx.getResource() == null;
		
		String entity = ctx.getCurrentState().getEntityName();
		EdmEntitySet entitySet = edmDataServices.getEdmEntitySet(entity);
		if (entitySet == null)
			throw new RuntimeException("Entity set not found [" + entity + "]");
		Iterable<EdmEntityType> entityTypes = edmDataServices.getEntityTypes();
		assert entity.equals(entitySet.getName());

		//Create entity key (simple types only)
		OEntityKey key;
		try {
			key = CommandHelper.createEntityKey(entityTypes, entity, ctx.getId());
		} catch(Exception e) {
		    LOGGER.warn("Failure to create the entity key.", e);
			return Result.FAILURE;
		}
		
		OEntityGetRequest<OEntity> request = getConsumer().getEntity(entity, key);
		
		/*
		 * Execute request
		 */
		OEntity oEntity = request.execute();
		
		EntityResource<OEntity> oer = CommandHelper.createEntityResource(oEntity);
		ctx.setResource(oer);		
		return Result.SUCCESS;
	}

	protected ODataConsumer getConsumer() {
		return consumer;
	}
	
}
