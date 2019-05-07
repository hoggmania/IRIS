package com.temenos.interaction.core.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.temenos.interaction.core.resource.ResourceMetadataManager;

/**
 * This class holds metadata information about resource entities.
 */
public class Metadata  {
	public final static String MODEL_SUFFIX = "Model";

	//Map of <Entity name, Entity metadata>
	private Map<String, EntityMetadata> entitiesMetadata = new ConcurrentHashMap<String, EntityMetadata>();
	private String modelName;

	private ResourceMetadataManager resourceMetadataManager;
	
	/**
	 * Construct a new metadata object
	 * @param modelName name of this model
	 * Some unit test are using this
	 */
	public Metadata(String modelName) {
		this.modelName = modelName;
	}

	/*
	 * construct metadata with ResourceMetadataManager
	 */
	public Metadata(ResourceMetadataManager resourceMetadataManager) {
		setResourceMetadataManager(resourceMetadataManager);
	}
	
	/**
	 * Returns the metadata of the specified entity
	 * @param entityName Entity name
	 * @return entity metadata
	 */
	public EntityMetadata getEntityMetadata(String entityName) {		
		if( !entitiesMetadata.containsKey(entityName)) {
			if(resourceMetadataManager == null) {
				resourceMetadataManager = new ResourceMetadataManager();
			}
			Metadata metadata = resourceMetadataManager.getMetadata(entityName);
			/*
			 * Protect against null.
			 */
			if (metadata == null){
				return null;
			}
			entitiesMetadata.putAll(metadata.getEntitiesMetadata());
			setModelName(metadata.getModelName());
		} 
		return entitiesMetadata.get(entityName);
	}
	
	/**
	 * Sets the metadata for the specified entity
	 * @param entityName Entity name
	 * @param entityMetadata Entity metadata
	 */
	public void setEntityMetadata(EntityMetadata entityMetadata) {
		entitiesMetadata.put(entityMetadata.getEntityName(), entityMetadata);
	}
	
	/**
	 * Returns a map of <entity name, entity metadata> 
	 * @return entities metadata map
	 */
	public Map<String, EntityMetadata> getEntitiesMetadata() {
		return entitiesMetadata;
	}

	/**
	 * Returns the name of the model
	 * @return model name
	 */
	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		if (this.modelName == null || this.modelName.isEmpty())
			this.modelName = modelName;
	}
	
	/*
	 * setter method 
	 */
	public void setResourceMetadataManager(ResourceMetadataManager rmManager) {
		this.resourceMetadataManager = rmManager;
	}
	
	public void unload( String entityName) {
		entitiesMetadata.remove(entityName);
	}

	/*
	 * return metadata
	 */
	public Metadata getMetadata() {
		return this;
	}	
}
