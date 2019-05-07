package com.temenos.useragent.generic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.useragent.generic.internal.EntityWrapper;

/**
 * This class maps {@link Entity entities} against some of their attributes to
 * provide convenient access.
 * 
 * @author ssethupathi
 *
 */
public class Entities {

	private EntityWrapper item;
	private List<EntityWrapper> collection;
	private boolean entitiesNotMapped = true;
	private Map<String, EntityWrapper> collectionEntitiesById = new HashMap<String, EntityWrapper>();

	/**
	 * Builds the instance for a response with collection of entities.
	 * 
	 * @param collection
	 *            of entities
	 */
	public Entities(List<EntityWrapper> collection) {
		this.collection = new ArrayList<EntityWrapper>();
		for (EntityWrapper entity : collection) {
			this.collection.add(entity);
		}
	}

	/**
	 * Builds the instance for a response with a single entity.
	 * 
	 * @param item
	 */
	public Entities(EntityWrapper item) {
		this.item = item;
	}

	/**
	 * Returns the {@link EntityWrapper entity} for the supplied <i>id</i>.
	 * 
	 * @param id
	 * @return entity
	 * @throws IllegalStateException
	 *             if the underlying response is not a collection type
	 * @see #isCollection()
	 */
	public EntityWrapper byId(String id) {
		if (entitiesNotMapped) {
			mapEntities();
		}
		return collectionEntitiesById.get(id);
	}

	/**
	 * Returns the {@link EntityWrapper entity} for the supplied <i>index</i>.
	 * 
	 * @param index
	 * @return entity
	 * @throws IllegalStateException
	 *             if the underlying response is not a collection type
	 * @see #isCollection()
	 */
	public EntityWrapper byIndex(int index) {
		if (isCollection()) {
			if (index >= 0 && index < collection.size()) {
				return collection.get(index);
			} else {
				throw new IllegalStateException("Invalid index '" + index
						+ "' for collection of size '" + collection.size()
						+ "'");
			}
		} else {
			throw new IllegalStateException("Not a collection response");
		}
	}

	/**
	 * Returns all {@link Entity entities} from this mapping.
	 * 
	 * @return entities
	 * @throws IllegalStateException
	 *             if the underlying response is not a collection type
	 * @see #isCollection()
	 */
	public List<? extends Entity> collection() {
		if (isCollection()) {
			return collection;
		} else {
			throw new IllegalStateException("Not a collection response");
		}
	}

	/**
	 * Returns the single {@link Entity entity} from the response.
	 * 
	 * @return item
	 * @throws IllegalStateException
	 *             if the underlying response is not an item type
	 * @see #isItem()
	 */
	public Entity item() {
		if (isItem()) {
			return item;
		} else {
			throw new IllegalStateException("Not a single item response");
		}
	}

	/**
	 * Returns whether or not the response contains an item entity.
	 * 
	 * @return true if response contains an item entity, false otherwise
	 */
	public boolean isItem() {
		return item != null;
	}

	/**
	 * Returns whether or not the response contains an collection of entities.
	 * 
	 * @return true if response contains a collection of entities, false
	 *         otherwise
	 */
	public boolean isCollection() {
		return collection != null;
	}

	private void mapEntities() {
		if (isCollection()) {
			for (EntityWrapper entity : collection) {
				collectionEntitiesById.put(entity.id(), entity);
			}
			entitiesNotMapped = false;
		} else {
			throw new IllegalStateException("Not a collection response");
		}
	}
}
