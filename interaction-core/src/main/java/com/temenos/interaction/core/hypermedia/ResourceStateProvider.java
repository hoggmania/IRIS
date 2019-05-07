package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Map;
import java.util.Set;

public interface ResourceStateProvider {

	/**
	 * Return true if resource state for name can be found
	 * 
	 * @param name   the id taken from a properties file,
	 *               *NOT* the name of the resource state
	 * @return
	 */
	public boolean isLoaded(String name);

	/**
	 * Lookup and return a single {@link ResourceState} by name
	 * 
     * @param name   the id taken from a properties file,
     *               *NOT* the name of the resource state
	 */
	public ResourceState getResourceState(String name);

	/**
	 * Using the supplied event and path return the {@link ResourceState} that
	 * is being requested.
	 * 
	 */
	public ResourceState determineState(Event event, String resourcePath);

	/**
	 * Return a map of resource state names where the path is the key.
	 * 
	 */
	public Map<String, Set<String>> getResourceStatesByPath();

	/**
	 * Return a map of resource methods accepted by a resources where the
	 * resource state name is the key.
	 * 
	 */
	public Map<String, Set<String>> getResourceMethodsByState();

	/**
	 * Return a map to a resource path where the resource state name is the key.
	 * 
	 */
	public Map<String, String> getResourcePathsByState();
	
    /**
     * Lookup and return a single {@link ResourceState} by HTTP method and URL.
     *  
     */
	public ResourceState getResourceState(String httpMethod, String url) throws MethodNotAllowedException;
	
    /**
     * Retrieves the resource state id from a HTPP method and URL. This is needed
     * to check, for instance, if a resource state is loaded when we do not have
     * the id (the ResourceState class doesn't have this information).
     *  
     * @return the id used to store the resource state with the provider
     * @throws MethodNotAllowedException if the URL is registered but not the method 
     */
    public String getResourceStateId(String httpMethod, String url) throws MethodNotAllowedException;
}
