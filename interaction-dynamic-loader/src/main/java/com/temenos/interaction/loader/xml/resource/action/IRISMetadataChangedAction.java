package com.temenos.interaction.loader.xml.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.loader.xml.XmlChangedEventImpl;
import com.temenos.interaction.odataext.entity.MetadataOData4j;

/**
 * This class performs the necessary updates to IRIS in memory meta data when the underlying meta data changes 
 *
 * @author mlambert
 *
 */
public class IRISMetadataChangedAction implements Action<XmlChangedEventImpl> {
	private Metadata metadata;
	private MetadataOData4j metadataOData4j;
	private Pattern pattern = Pattern.compile(".*-(.*).xml");
	
	private final Logger logger = LoggerFactory.getLogger(IRISMetadataChangedAction.class);	
		
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * @param metadataOData4j the metadataOData4j to set
	 */
	public void setMetadataOData4j(MetadataOData4j metadataOData4j) {
		this.metadataOData4j = metadataOData4j;
	}
	
	@Override
	public void execute(XmlChangedEventImpl event) {
		String filename = event.getResource().getFilename();

		// Unload all meta data relating to the entity

		Matcher matcher = pattern.matcher(filename);
		
		if(!matcher.find()) {
			logger.warn("Failed to retrieve entity name from " + filename);
			
			return;
		}
		
		String entityName = matcher.group(1);
		
		// Unload IRIS internal meta data
		metadata.unload(entityName);

		// Unload EDM meta data
		metadataOData4j.unloadMetadata(entityName);
	}
}
