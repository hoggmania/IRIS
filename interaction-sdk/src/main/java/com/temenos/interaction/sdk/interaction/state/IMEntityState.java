package com.temenos.interaction.sdk.interaction.state;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.HashSet;
import java.util.Set;

/**
 * This class holds information about a resource state
 */
public class IMEntityState extends IMState implements IMAction {

	private Set<String> actions = new HashSet<String>();		//Actions
	
	/**
	 * Construct a new resource state 
	 * @param name resource state name
	 * @param path URI path associated to this resource state
	 */
	public IMEntityState(String name, String path) {
		super(name, path, null);
	}
	
	/**
	 * Construct a new resource state 
	 * @param name resource state name
	 * @param path URI path associated to this resource state
	 * @param view View to execute
	 */
	public IMEntityState(String name, String path, String view) {
		super(name, path, view);
	}

	/**
	 * Construct a new resource state which triggers an action
	 * @param name resource state name
	 * @param path URI path associated to this resource state
	 * @param relations link relations
	 * @param action action to execute
	 */
	public IMEntityState(String name, String path, String relations, String action) {
		super(name, path, null, relations);
		this.actions.add(action);
	}
	
	public boolean hasView() {
		return super.getView() != null;
	}

	@Override
	public boolean hasActions() {
		return actions.size() > 0;
	}
	
	@Override
	public Set<String> getActions() {
		return actions;
	}
}
