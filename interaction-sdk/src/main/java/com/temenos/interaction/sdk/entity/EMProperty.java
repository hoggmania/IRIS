package com.temenos.interaction.sdk.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class holds information about an entity field
 */
public class EMProperty {
	private String name;
	private List<EMTerm> terms = new ArrayList<EMTerm>();
	private Map<String, EMProperty> childProperties = new HashMap<String, EMProperty>();

	public EMProperty(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this property
	 * 
	 * @return property name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns all the vocabulary terms of this property
	 * 
	 * @return all vocabulary terms
	 */
	public List<EMTerm> getVocabularyTerms() {
		return terms;
	}

	/**
	 * Adds a term to this property vocabulary.
	 * 
	 * @param term
	 */
	public void addVocabularyTerm(EMTerm term) {
		terms.add(term);
	}

	/**
	 * Returns <i>true</i> this property has a child property for the name child
	 * property name.
	 * 
	 * @param childPropertyName
	 * @return true if this property contains a child property for the child
	 *         property name, false otherwise.
	 */
	public boolean hasChildProperty(String childPropertyName) {
		return childProperties.containsKey(childPropertyName);
	}

	/**
	 * Adds a child property this this property.
	 * 
	 * @param childProperty
	 */
	public void addChildProperty(EMProperty childProperty) {
		childProperties.put(childProperty.getName(), childProperty);
	}

	/**
	 * Returns the child property for the child property name.
	 * 
	 * @param childName
	 * @return child property
	 */
	public EMProperty getChildProperty(String childName) {
		return childProperties.get(childName);
	}

	/**
	 * Returns <i>true</i> if this property has any child property.
	 * 
	 * @return true if this property has any child property, false otherwise.
	 */
	public boolean hasChildren() {
		return !childProperties.isEmpty();
	}

	/**
	 * Returns all the child properties of this property.
	 * 
	 * @return child properties
	 */
	public Collection<EMProperty> getChildProperties() {
		return childProperties.values();
	}
}
