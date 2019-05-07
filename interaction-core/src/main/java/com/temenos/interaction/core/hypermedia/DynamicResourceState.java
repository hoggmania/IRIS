package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.List;

/**
 * Instances of this class represent placeholder states in use cases where transitions are determined by selection of an item from a prior response; at runtime the real target
 * state will be resolved using a resource locator 
 *
 * @author mlambert
 *
 */
public class DynamicResourceState extends ResourceState {
	private String resourceLocatorName;
	private String[] resourceLocatorArgs;
	
	/**
	 * @param entityName
	 * @param name
	 * @param actions
	 * @param path
	 */
	public DynamicResourceState(String entityName, String name, String resourceLocatorName, String... resourceLocatorArgs) {
		super(entityName, name, new ArrayList<Action>(), "DYNAMIC");
		this.resourceLocatorName = resourceLocatorName;
		this.resourceLocatorArgs = resourceLocatorArgs;
	}		

	/**
	 * @param parent
	 * @param name
	 * @param actions
	 * @param path
	 */
	public DynamicResourceState(ResourceState parent, String name, List<Action> actions, String path, String resourceLocatorName, String... resourceLocatorArgs) {
		super(parent, name, actions, path);
		this.resourceLocatorName = resourceLocatorName;
		this.resourceLocatorArgs = resourceLocatorArgs;		
	}

	@Override
	public boolean isPseudoState() {
		// True as any instance of this class represents a placeholder state
		return true;
	}

	/**
	 * @return the resourceLocatorName
	 */
	public String getResourceLocatorName() {
		return resourceLocatorName;
	}

	/**
	 * @return the resourceLocatorArgs
	 */
	public String[] getResourceLocatorArgs() {
		return resourceLocatorArgs;
	}		
}
