package com.temenos.interaction.core.entity;

import java.util.Collection;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 * An Entity property class can hold either simple or complex types.
 */
public class EntityProperty implements EntityTreeNode {

	private EntityTreeNode parent;
	private final String name;
	private final Object value;

	public EntityProperty(String name, Object value) {
		this.name = name;
		this.value = value;
		setParentForChildNodes();
	}

	/**
	 * Returns the name of this property.
	 * 
	 * @return property name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the value of this property.
	 * 
	 * @return property value
	 */
	public Object getValue() {
		return value;
	}

	@Override
	public void setParent(EntityTreeNode parent) {
		this.parent = parent;
	}

	@Override
	public String getFullyQualifiedName() {
		if (parent != null) {
			if (!parent.getFullyQualifiedName().isEmpty()) {
				return parent.getFullyQualifiedName() + "." + name;
			}
		}
		return name;
	}

	// sets this node as parent to it's non-value child nodes
	private void setParentForChildNodes() {
		if (value instanceof EntityTreeNode) {
			((EntityTreeNode) value).setParent(this);
		} else if (value instanceof Collection) {
			for (Object childProperty : (Collection<?>) value) {
				if (childProperty instanceof EntityTreeNode) {
					((EntityTreeNode) childProperty).setParent(this);
				}
			}
		}
	}
}
