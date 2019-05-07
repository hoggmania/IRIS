package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

import com.temenos.useragent.generic.Link;

/**
 * Defines the payload which is received as in the body after a Http method
 * execution.
 * 
 * @author ssethupathi
 *
 */
public interface Payload {

	/**
	 * Defines whether or no this payload is a collection of items.
	 * 
	 * @return true if it's collection, false for single item
	 */
	boolean isCollection();

	/**
	 * Returns the links available in the payload
	 * 
	 * @return links
	 */
	List<Link> links();

	/**
	 * Returns the entity representing the single item in the payload. If this
	 * payload represents a collection then this method would return null.
	 * 
	 * @see #isCollection()
	 * @see #entities()
	 * 
	 * @return single item in the payload
	 */
	EntityWrapper entity();

	/**
	 * Returns the entities representing the collection of items in the payload.
	 * If this payload represents an item then this method would return an empty
	 * list.
	 * 
	 * @see #isCollection()
	 * @see #entity()
	 * 
	 * @return all items in the payload
	 */
	List<EntityWrapper> entities();
}
