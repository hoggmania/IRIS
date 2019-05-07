package com.temenos.interaction.core.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlTransient;

import com.temenos.interaction.core.hypermedia.Link;
import com.temenos.interaction.core.hypermedia.Transition;

/**
 * A CollectionResource is the RESTful representation of a collection of
 * 'things' within our system.  A 'thing' is addressable by a globally 
 * unique key, it has a set of simple & complex named properties, and a set 
 * of links to find other resources linked to this resource.
 * @author aphethean
 */
public class CollectionResource<T> implements RESTResource {
	
	private Collection<EntityResource<T>> entities;

	// TODO implement JAXB Adapter for OProperty
//	private List<OProperty<?>> properties;

	@XmlTransient
	private String entitySetName;
	// links from a collection
	@XmlTransient
    private Collection<Link> links;
	@XmlTransient
    private Map<Transition, RESTResource> embedded;
	@XmlTransient
    private String entityTag = null;
	@XmlTransient
	private Integer inlineCount;
	@XmlTransient
	private String queryToken = null;
	@XmlTransient
	private String skipToken = null;
	@XmlTransient
    private Map<String,String> columnProperty = new HashMap<String,String>();
	@XmlTransient
	private String tokenValue = null;
	
	public CollectionResource() {}

	/**
	 * Construct a new instance of a CollectionResource.  EntitySetName will be set by the interaction-core
	 * before passing to a Provider
	 * @param entities
	 */
	public CollectionResource(Collection<EntityResource<T>> entities) {
		this.entities = entities;
	}

	/**
	 * This constructor expected to be used internally.
	 * @param entitySetName
	 * @param entities
	 */
	public CollectionResource(String entitySetName, Collection<EntityResource<T>> entities) {
		this.entitySetName = entitySetName;
		this.entities = entities;
	}

	public String getEntitySetName() {
		return entitySetName;
	}
	
	public Collection<EntityResource<T>> getEntities() {
		return entities;
	}
	
	@Override
	public GenericEntity<CollectionResource<T>> getGenericEntity() {
		return new GenericEntity<CollectionResource<T>>(this, this.getClass().getGenericSuperclass());
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
		return entitySetName;
	}

	@Override
	public void setEntityName(String entityName) {
		this.entitySetName = entityName;
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
	 * Sets the inline count value of the collection response.
	 * 
	 * @param inline
	 *            count
	 */
	public void setInlineCount(Integer inlineCount) {
		this.inlineCount = inlineCount;
	}

	/**
	 * Returns the inline count value of the collection response.
	 * 
	 * @return inline count or null if inline count is not available
	 */
	public Integer getInlineCount() {
		return this.inlineCount;
	}
	/* it have been considered the second position will be the skip token value */
    public void setSkipToken(String token) {
        if (token != null && !token.isEmpty()) {
            String tokenvalue = setSkipAndQueryTokenValue(token);
            if (tokenvalue != null && !tokenvalue.isEmpty()) {
                String[] skipToken = tokenvalue.split(" & ");
                if (skipToken.length > 1) {
                    this.skipToken = skipToken[1];
                }
            }
        }

    }

    public String getSkipToken() {
        return this.skipToken;
    }
    
    /*this splits up the skiptoken and query token*/
    public String setSkipAndQueryTokenValue(String token) {
        if (token != null && !token.isEmpty()) {
            String[] skipAndQueryTokenValue = token.split(" \\^ ");
            if (skipAndQueryTokenValue.length > 0) {
                return this.tokenValue = skipAndQueryTokenValue[0];
            }
        }
        return null;
    }
    
    /*it set the column values as a map for the dynamic attributes sent*/
    public void setColumnValue(String token) {
        if (token != null && !token.isEmpty()) {
            String[] columnValue = token.split(" \\^ ");
            if (columnValue.length > 1) {
                String[] columnValuePairs = columnValue[1].toString().split("\\_");
                if (columnValuePairs != null) {
                    for (String columnValuePair : columnValuePairs) {
                        String[] splitColumnValuePair = columnValuePair.split("=");
                        String hiddenColumnName = splitColumnValuePair[0];
                        String hiddenColumnValue = splitColumnValuePair[1];
                        this.columnProperty.put(hiddenColumnName, hiddenColumnValue);

                    }
                }
            }
        }
    }
    
    public Map<String,String> getHiddenColumnValue(){
        return this.columnProperty;
    }
    
    
    /* it have been considered the first position will be the query token value */
    public void setQueryToken(String token) {
        if (token != null && !token.isEmpty()) {
            String tokenvalue = setSkipAndQueryTokenValue(token);
            if (tokenvalue != null && !tokenvalue.isEmpty()) {
                String[] queryToken = tokenvalue.split(" & ");
                if (queryToken.length > 0) {
                    this.queryToken = queryToken[0];
                }
            }

        }
    }

	public String getQueryToken() {
	    return this.queryToken;
	}
}
