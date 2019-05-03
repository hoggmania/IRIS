package com.temenos.interaction.commands.odata;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.odata4j.edm.EdmDataServices;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.resource.MetaDataResource;
import com.temenos.interaction.odataext.entity.MetadataOData4j;

/**
 * GET command for obtaining meta data defining either the
 * resource model or the service document. 
 */
public class GETMetadataCommand implements InteractionCommand {

	// command configuration
	// TODO remove this when we no longer use a MetaDataResource
	private String resourceToProvide;
	private MetadataOData4j metadataOData4j;

	
	/**
	 * Construct an instance of this command
	 * @param resourceToProvide Configure this command to provide either an EntityResource for the
	 * @param metadataOData4j contain resource metadata.
	 */
	public GETMetadataCommand(String resourceToProvide, MetadataOData4j metadataOData4j) {
		this.resourceToProvide = resourceToProvide;
		this.metadataOData4j = metadataOData4j;
	}	
	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) {
		assert(ctx != null);
		if(resourceToProvide.equals("ServiceDocument")) {
			EntityResource<EdmDataServices> sdr = 
					CommandHelper.createServiceDocumentResource(metadataOData4j.getMetadata());
			ctx.setResource(sdr);
		} else {
			MetaDataResource<EdmDataServices> mdr = 
					CommandHelper.createMetaDataResource(metadataOData4j.getMetadata());
			ctx.setResource(mdr);
		}
		return Result.SUCCESS;
	}

}
