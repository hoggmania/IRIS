package com.temenos.interaction.core.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.Map;

import javax.ws.rs.core.GenericEntity;

import com.temenos.interaction.core.hypermedia.Link;
import com.temenos.interaction.core.hypermedia.Transition;

/**
 * A RESTResource is the base interface for all types of resources.
 * 
 * @author aphethean
 */
public interface RESTResource {

	/**
	 * Wrap this resource into a JAX-RS GenericEntity object
	 * @return GenericEntity object
	 */
	public GenericEntity<?> getGenericEntity();
	
	/**
	 * Return the links from this resource to another.
	 * @return Collection<Link>
	 */
    public Collection<Link> getLinks();

    /**
     * Called during resource building phase to set the links for
     * serialization by the provider.
     * @param links
     */
    public void setLinks(Collection<Link> links);

    /**
     * Called during resource building phase to set the embedded
     * resources for serialization by the provider.
     * @param embedded
     */
    public void setEmbedded(Map<Transition, RESTResource> embedded);

	/**
	 * Return the embedded resources.
	 * @return Map<Transition, RESTResource>
	 */
    public Map<Transition, RESTResource> getEmbedded();
    
	/**
	 * Return the entity name for this resource.
	 * @return String
	 */
    public String getEntityName();

    /**
     * Called during resource building phase to set the entity name for 
     * use by the provider.
     * @param links
     */
    public void setEntityName(String entityName);

	/**
	 * Return the entity tag for this resource.
	 * @return etag
	 */
    public String getEntityTag();

    /**
     * Set the entity tag for this resource.
     * @param etag
     */
    public void setEntityTag(String entityTag);
}  