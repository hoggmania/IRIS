package com.temenos.interaction.odataext;

import java.util.Iterator;

import org.odata4j.edm.EdmDataServices;
import org.odata4j.edm.EdmEntitySet;
import org.odata4j.edm.EdmEntityType;
import org.odata4j.edm.EdmSchema;
import org.odata4j.edm.EdmType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Helper class which can be used by any OData dependent project.
 * 
 * @author sjunejo
 *
 */
public class ODataHelper {

	private final static Logger logger = LoggerFactory.getLogger(ODataHelper.class);
	
	/**
	 * Returns the entity set holding the specified entity (type) name
	 * @param entityName entity type name
	 * @param edmDataServices metadata
	 * @return entity set
	 * @throws Exception if entity set cannot be found
	 */
	public static EdmEntitySet getEntitySet(String entityName, EdmDataServices edmDataServices) throws Exception {
		//Find entity type
		EdmType entityType = null;
		Iterator<EdmSchema> itSchema = edmDataServices.getSchemas().iterator();
		while(entityType == null && itSchema.hasNext()) {
			entityType = edmDataServices.findEdmEntityType(itSchema.next().getNamespace() + "." + entityName);
		}
		if(entityType == null || !(entityType instanceof EdmEntityType)) {
			throw new Exception(String.format("Entity type %s does not exist", entityName));
		}
		
		//Find entity set
		EdmEntitySet entitySet = null;
		try {
			entitySet = edmDataServices.getEdmEntitySet((EdmEntityType) entityType);
		} catch (Exception e) {
			logger.debug("Entity set does not exist for " + entityName, e);
		}
		return entitySet;
	}
	
}
