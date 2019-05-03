package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.List;

public class CollectionResourceState extends ResourceState {
    private static final String[] RELATIONS = new String[]{ "collection" };

	public CollectionResourceState(String entityName, String name, List<Action> actions, String path) {
		super(entityName, name, actions, path, RELATIONS);
	}
	public CollectionResourceState(String entityName, String name, List<Action> actions, String path, UriSpecification uriSpec) {
		super(entityName, name, actions, path, RELATIONS, uriSpec);
	}
	public CollectionResourceState(String entityName, String name, List<Action> actions, String path, String[] rels, UriSpecification uriSpec) {
		super(entityName, name, actions, path, rels != null ? rels : RELATIONS, uriSpec);
	}
	public CollectionResourceState(String entityName, String name, List<Action> actions, String path, String[] rels, UriSpecification uriSpec, ResourceState errorState) {
		super(entityName, name, actions, path, rels != null ? rels : RELATIONS, uriSpec, errorState);
	}
	
}
