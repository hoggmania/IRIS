package com.interaction.example.odata.etag.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.odata4j.core.OEntity;
import org.odata4j.core.OProperty;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.resource.RESTResource;

public class GETEntityWithEtagCommand implements InteractionCommand {

	private InteractionCommand getEntityCommand;
	
	public GETEntityWithEtagCommand(InteractionCommand getEntityCommand) {
		this.getEntityCommand = getEntityCommand;
	}
	
	/* Implement InteractionCommand interface */
	
	@SuppressWarnings("unchecked")
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		Result result = getEntityCommand.execute(ctx);
		RESTResource resource = ctx.getResource();
		if(resource != null) {
			EntityResource<OEntity> er = (EntityResource<OEntity>) ctx.getResource();
			resource.setEntityTag(getEtag(er.getEntity()));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getEtag(InteractionContext ctx) throws InteractionException {
		Result result = execute(ctx);
		if(result == Result.SUCCESS) {
			EntityResource<OEntity> er = (EntityResource<OEntity>) ctx.getResource();
			return getEtag(er.getEntity());
		}
		return null;
	}
	
	public String getEtag(OEntity entity) {
		String etag = "";
		List<OProperty<?>> props = new ArrayList<OProperty<?>>(entity.getProperties());
		Collections.sort(props, new Comparator<OProperty<?>>(){
		    public int compare(OProperty<?> p1, OProperty<?> p2) {
		        return p1.getName().compareToIgnoreCase(p2.getName());
		    }
		});
		for(OProperty<?> prop : props) {
			if(!etag.isEmpty()) {
				etag += ", ";
			}
			etag += prop.getName() + "=" + prop.getValue();
		}
		etag = entity.getEntitySetName() + "[" + etag + "]";
		return etag;
	}
}
