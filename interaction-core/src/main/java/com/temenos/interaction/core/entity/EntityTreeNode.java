package com.temenos.interaction.core.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 * Defines a node in a tree of properties of an entity.
 */
public interface EntityTreeNode {

	/**
	 * Returns the fully qualified name of this node. The fully qualified name
	 * of this node is in the form
	 * <i>fullyQualifiedNameOfParentNode.thisNodeName</i>
	 * 
	 * @return fully qualified name of this node
	 */
	public String getFullyQualifiedName();

	/**
	 * Sets the parent node.
	 * 
	 * @param parent
	 *            node
	 */
	public void setParent(EntityTreeNode parent);
}
