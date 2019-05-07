package org.odata4j.format.xml;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.io.Reader;

import org.odata4j.core.OEntityKey;
import org.odata4j.edm.EdmDataServices;
import org.odata4j.format.Entry;
import org.odata4j.internal.FeedCustomizationMapping;

import com.temenos.interaction.odataext.entity.MetadataOData4j;

public class AtomEntryFormatParserExt extends AtomEntryFormatParser {

	MetadataOData4j metadataOdata4j;
	
	public AtomEntryFormatParserExt(MetadataOData4j metadataOdata4j,
			String entitySetName, OEntityKey entityKey,
			FeedCustomizationMapping fcMapping) {
		super(metadataOdata4j.getMetadata(), entitySetName, entityKey, fcMapping);
		
		this.metadataOdata4j = metadataOdata4j;
	}
	
	public AtomEntryFormatParserExt(EdmDataServices metadata,
			String entitySetName, OEntityKey entityKey,
			FeedCustomizationMapping fcMapping) {
		super(metadata, entitySetName, entityKey, fcMapping);
	}
	
	
	 @Override
	  public Entry parse(Reader reader) {
		 AtomFeedFormatParserExt parser = null;
		 
		if(metadataOdata4j == null) {
			parser = new AtomFeedFormatParserExt(metadata, entitySetName, entityKey, fcMapping);			
		} else {
		    parser = new AtomFeedFormatParserExt(metadataOdata4j, entitySetName, entityKey, fcMapping);			
		}
		
		return parser.parse(reader).entries.iterator().next();
	  }


}
