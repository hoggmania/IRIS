package com.temenos.interaction.core.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.InputStream;

import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.entity.vocabulary.TermFactory;

/**
 * 
 * This class provides an abstraction from the underlying mechanism used to load
 * config files
 *
 *
 * 
 * @author mohamednazir
 *
 */
public interface AbstractConfigLoaders {

    public InputStream load(String filename) throws Exception;

    public Metadata parseMetadataXML(String entityName, TermFactory termFactory);
    
    public Metadata parseMetadataXML(TermFactory termFactory);

    public void setIrisConfigDirPath(String location);

}
