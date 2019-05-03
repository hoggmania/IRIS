package com.temenos.interaction.example.hateoas.dynamic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.temenos.interaction.core.hypermedia.ResourceLocator;
import com.temenos.interaction.core.hypermedia.ResourceState;

/**
 * A mock resource locator
 *
 * @author mlambert
 *
 */
public class NoteResourceLocator implements ResourceLocator {	
	private final Map<String, ResourceState> aliasToResourceState = new HashMap<String, ResourceState>();
	
	public NoteResourceLocator(ResourceState state, Map<String, ResourceState> additionalAliasToResourceState) {
		collectResourceStatesByName(aliasToResourceState, new ArrayList<ResourceState>(), state);
		
		aliasToResourceState.putAll(additionalAliasToResourceState);
	}
		
	@Override
	public ResourceState resolve(Object... alias) {
		if(aliasToResourceState.containsKey(alias[0])) {
			 return aliasToResourceState.get(alias[0]);	
		} else {
			throw new RuntimeException("Invalid resource state supplied: " + alias[0]);							
		}		
	}
		
	private void collectResourceStatesByName(Map<String, ResourceState> result, Collection<ResourceState> states, ResourceState currentState) {
		if (currentState == null || states.contains(currentState)) {
			return;
		}
		
		states.add(currentState);
		
		// add current state to results
		result.put(currentState.getName(), currentState);
		
		for (ResourceState next : currentState.getAllTargets()) {
			if (!next.equals(currentState)) {
				String name = next.getName();
				result.put(name, next);
			}
			
			// Recurse
			collectResourceStatesByName(result, states, next);
		}
	}	
}
