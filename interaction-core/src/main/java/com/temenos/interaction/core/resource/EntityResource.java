package com.temenos.interaction.core.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.Map;

import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.temenos.interaction.core.hypermedia.Link;
import com.temenos.interaction.core.hypermedia.Transition;

/**
 * An EntityResource is the RESTful representation of a 'thing' within our
 * system.  A 'thing' is addressable by a globally unique key, it has a set of
 * simple & complex named properties, and a set of links to find other resources
 * linked to this resource.
 * @author aphethean
 */
@XmlRootElement(name = "resource")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityResource<T> implements RESTResource, Cloneable {
	@XmlAnyElement(lax=true)
	private T entity;
	
	/* injected by during build response phase */
	@XmlTransient
    private String entityName;
	@XmlTransient
    private Collection<Link> links;
	@XmlTransient
    private Map<Transition, RESTResource> embedded;
	@XmlTransient
    private String entityTag = null;

	public EntityResource() {
	}
	
	public EntityResource(String entityName, T entity) {
		this.entityName = entityName;
		this.entity = entity;
	}

	public EntityResource(T entity) {
		this.entity = entity;
	}
	
	public T getEntity() {
		return entity;
	}

	@Override
	public GenericEntity<EntityResource<T>> getGenericEntity() {
		return new GenericEntity<EntityResource<T>>(this, this.getClass().getGenericSuperclass());
	}

	@Override
    public Collection<Link> getLinks() {
    	return this.links;
    }

    /**
     * Called during resource building phase to set the links for
     * serialization by the provider.
     * @param links
     */
	@Override
    public void setLinks(Collection<Link> links) {
    	this.links = links;
    }

	@Override
    public Map<Transition, RESTResource> getEmbedded() {
    	return this.embedded;
    }

    /**
     * Called during resource building phase to set the embedded
     * resources for serialization by the provider.
     * @param embedded
     */
	@Override
    public void setEmbedded(Map<Transition, RESTResource> embedded) {
    	this.embedded = embedded;
    }

	@Override
	public String getEntityName() {
		return entityName;
	}

	@Override
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String getEntityTag() {
		return entityTag;
	}

	@Override
	public void setEntityTag(String entityTag) {
		this.entityTag = entityTag;
	}

	/**
	 * Create a shallow copy of this EntityResource using the same entity, links and etag
	 * as this instance.
	 * @return
	 */
	@Override
	public EntityResource<T> clone() throws CloneNotSupportedException {
	    super.clone();
		EntityResource<T> entityResourceClone = this.createNewEntityResource(this.entityName, this.entity);
		entityResourceClone.setEmbedded(this.embedded);
		entityResourceClone.setLinks(this.links);
		entityResourceClone.setEntityTag(this.entityTag);
		return entityResourceClone;
	}
	
	protected <E> EntityResource<E> createNewEntityResource(String name, E entity){
		return new EntityResource<E>(name, entity);
	}
}
