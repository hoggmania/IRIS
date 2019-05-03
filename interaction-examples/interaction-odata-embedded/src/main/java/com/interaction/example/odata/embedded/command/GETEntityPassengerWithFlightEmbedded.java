package com.interaction.example.odata.embedded.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.entity.Entity;
import com.temenos.interaction.core.entity.EntityProperties;
import com.temenos.interaction.core.entity.EntityProperty;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.hypermedia.Transition;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.resource.RESTResource;

public class GETEntityPassengerWithFlightEmbedded implements InteractionCommand  {

	public GETEntityPassengerWithFlightEmbedded() {
	}
	
	/* Implement InteractionCommand interface */
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		
		// GET the Passenger (use Entity to test the AtomEntityEntryProvider
		EntityProperties pProperties = new EntityProperties();
		pProperties.setProperty(new EntityProperty("name", "Big Ron"));
		pProperties.setProperty(new EntityProperty("flightID", "2629"));
		Entity pEntity = new Entity("Passenger", pProperties);
		EntityResource<Entity> pEntityResource = new EntityResource<Entity>(pEntity);
		pEntityResource.setEntityName(pEntity.getName());
		ctx.setResource(pEntityResource);

		// find the Flight Transition
		ResourceState passenger_flight = null;
		Collection<ResourceState> targets = ctx.getCurrentState().getAllTargets();
		for (ResourceState target : targets) {
			if (target.getName().equals("passenger_flight")) {
				passenger_flight = target;
				break;
			}
		}
		Transition pfTranstion = ctx.getCurrentState().getTransition(passenger_flight);
		
		// embed the Flight
		EntityProperties fProperties = new EntityProperties();
		fProperties.setProperty(new EntityProperty("flightID", 2629));
		fProperties.setProperty(new EntityProperty("passengerNo", 123));
		Entity fEntity = new Entity("Flight", fProperties);
		Map<Transition, RESTResource> embeddedResources = new HashMap<Transition, RESTResource>();
		EntityResource<Entity> fEntityResource = new EntityResource<Entity>(fEntity);
		fEntityResource.setEntityName(pEntity.getName());
		embeddedResources.put(pfTranstion, fEntityResource);
		ctx.getResource().setEmbedded(embeddedResources);
		
		return Result.SUCCESS;
	}

}
