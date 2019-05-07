package com.temenos.interaction.core.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * An Entity contains a set of properties.  
 */
public class Entity {

	private final String name;
	private final EntityProperties properties;
	
	public Entity(String name, EntityProperties properties) {
		this.name = name;
		this.properties = properties;
	}
	
	/**
	 * Returns the name of this entity.
	 * @return Entity name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the properties of this entity.
	 * @return Entity properties
	 */
	public EntityProperties getProperties() {
		return properties;
	}
}
