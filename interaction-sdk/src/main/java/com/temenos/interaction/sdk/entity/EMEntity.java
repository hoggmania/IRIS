package com.temenos.interaction.sdk.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds information about an entity
 */
public class EMEntity {
	private String name;
	private Map<String, EMProperty> properties = new HashMap<String, EMProperty>();

	public EMEntity(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this entity.
	 * @return entity name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns all the properties belong to this entity.
	 * @return properties
	 */
	public Collection<EMProperty> getProperties() {
		return properties.values();
	}

	/**
	 * Adds a property to this entity.
	 * @param property
	 */
	public void addProperty(EMProperty property) {
		properties.put(property.getName(), property);
	}

	/**
	 * Returns <i>true</i> if this entity contains a property with the property
	 * name.
	 * 
	 * @param propertyName
	 * @return true if this entity contains the property, false otherwise
	 */
	public boolean contains(String propertyName) {
		return properties.containsKey(propertyName);
	}

	/**
	 * Returns the {@link EMProperty} instance for the property name. If this entity does not
	 * contain a property for the property name then <i>null</i> is returned.
	 * 
	 * @param propertyName
	 * @return property
	 */
	public EMProperty getProperty(String propertyName) {
		return properties.get(propertyName);
	}
}
