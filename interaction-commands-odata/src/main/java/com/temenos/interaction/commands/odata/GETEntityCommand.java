package com.temenos.interaction.commands.odata;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;

import org.odata4j.core.OEntity;
import org.odata4j.core.OEntityKey;
import org.odata4j.edm.EdmEntitySet;
import org.odata4j.exceptions.NotFoundException;
import org.odata4j.exceptions.ODataProducerException;
import org.odata4j.producer.EntityQueryInfo;
import org.odata4j.producer.EntityResponse;
import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.resources.OptionsQueryParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.command.InteractionProducerException;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.odataext.entity.MetadataOData4j;

public class GETEntityCommand extends AbstractODataCommand implements InteractionCommand {
	private final static Logger logger = LoggerFactory.getLogger(GETEntityCommand.class);

	public GETEntityCommand(ODataProducer producer) {
		super(producer);
	}
	
	public GETEntityCommand(MetadataOData4j metadataOData4j, ODataProducer producer) {
		super(metadataOData4j, producer);
	}
	
	protected ODataProducer getProducer() {
		return producer;
	}

	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		assert(ctx.getCurrentState() != null);
		assert(ctx.getCurrentState().getEntityName() != null && !ctx.getCurrentState().getEntityName().equals(""));
		
		String entityName = getEntityName(ctx);
		logger.debug("Getting entity for " + entityName);
		try {
			EdmEntitySet entitySet = getEdmEntitySet(entityName);

			//Create entity key (simple types only)
			OEntityKey key = CommandHelper.createEntityKey(entitySet, ctx.getId());
			
			//Get the entity
			String entitySetName = entitySet.getName();			
			EntityResponse er = getProducer().getEntity(entitySetName, key, getEntityQueryInfo(ctx));
			OEntity entity = er.getEntity();
			EntityResource<OEntity> oer = CommandHelper.createEntityResource(entity);
			oer.setEntityTag(entity.getEntityTag());		//Set the E-Tag
			ctx.setResource(oer);		
		} catch (InteractionProducerException ipe) {
			if (logger.isDebugEnabled()) {
				logger.debug("GET entities on [" + entityName + ", " + ctx.getId() + "] failed: ", ipe.getMessage());
			}			
			ctx.setResource(ipe.getEntityResource());
			throw new InteractionException(ipe.getHttpStatus(), ipe);
		} catch(NotFoundException ope) {
			logger.debug("GET entity on [" + entityName + ", " + ctx.getId() + "] failed: ", ope);
	    	// do not even think about changing this back to an exception, not found is a failure to GET
			return Result.FAILURE;
		} catch(ODataProducerException ope) {
			logger.debug("GET entity on [" + entityName + ", " + ctx.getId() + "] failed: ", ope);
			throw new InteractionException(ope.getHttpStatus(), ope);
		} catch(Exception e) {
			logger.error("Failed to GET entity [" + entityName + ", " + ctx.getId() + "]: ", e);
			throw new InteractionException(Status.INTERNAL_SERVER_ERROR, e);
		}
		return Result.SUCCESS;
	}

	/*
	 * Obtain the odata query information from the context's query parameters
	 * @param ctx interaction context
	 * @return query details
	 */
	private EntityQueryInfo getEntityQueryInfo(InteractionContext ctx) {
		MultivaluedMap<String, String> queryParams = ctx.getQueryParameters();
		String actionFilter = CommandHelper.getViewActionProperty(ctx, "filter");		//Filter defined as action property 
		String filter = queryParams.getFirst("$filter");								//Query filter
		if(actionFilter != null && !actionFilter.isEmpty()) {
			filter = filter != null ? filter + " and " + actionFilter : actionFilter;
		}
		String expand = queryParams.getFirst("$expand");
		String actionSelect = CommandHelper.getViewActionProperty(ctx, "select");
		String select = queryParams.getFirst("$select");
		if(actionSelect != null && !actionSelect.isEmpty()) {
		    select = select != null ? select + " and " + actionSelect : actionSelect;
        }
		
		// Capture all query parameters 
		Map<String, String> customOptions = CommandHelper.populateCustomOptionsMap(ctx);
			      
		return new EntityQueryInfo(
				OptionsQueryParser.parseFilter(filter),
				customOptions,
				OptionsQueryParser.parseExpand(expand),
				OptionsQueryParser.parseSelect(select));		
	}
}
