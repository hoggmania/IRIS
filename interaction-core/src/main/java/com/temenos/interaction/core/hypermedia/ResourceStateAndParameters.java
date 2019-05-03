package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



/**
 * TODO: Document me!
 *
 * @author mlambert
 *
 */
public class ResourceStateAndParameters {
	private ResourceState state;
	private ParameterAndValue[] paramsAndValues;
	
	/**
	 * @return the state
	 */
	public ResourceState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(ResourceState state) {
		this.state = state;
	}
	/**
	 * @return the params
	 */
	public ParameterAndValue[] getParams() {
		return paramsAndValues;
	}
	/**
	 * @param paramsAndValues the params to set
	 */
	public void setParams(ParameterAndValue[] params) {
		this.paramsAndValues = params;
	}
	
	
}
