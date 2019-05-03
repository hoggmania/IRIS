package com.temenos.interaction.sdk.interaction.state;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.sdk.interaction.IMResourceStateMachine;

/**
 * This class holds information about a resource state
 */
public class IMNavigationState extends IMState {

	private boolean toCollectionResource;		//true collection state, false entity state
	private IMResourceStateMachine resourceStateMachine;		//Target RSM
	private String linkProperty;		// the name of the field that joins this entity to the source entity

	public IMNavigationState(String name, String path, IMResourceStateMachine resourceStateMachine, String linkProperty, boolean toCollectionResource) {
		super(name, path, null);
		this.resourceStateMachine = resourceStateMachine;
		this.toCollectionResource = toCollectionResource;
		this.linkProperty = linkProperty;
	}
	
	public IMResourceStateMachine getTargetResourceStateMachine() {
		 return resourceStateMachine;
	}
	
	public String getLinkProperty() {
		return linkProperty;
	}
	
	/**
	 * Returns whether this is a navigation to a collection
	 * or an entity resource.
	 * @return true or false
	 */
	public boolean isNavigationToCollectionResource() {
		return toCollectionResource;
	}
}
