package com.temenos.interaction.metadata.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.InputStream;
import java.util.List;

/**
 * Interface which provides IRIS metadata from various sources.
 *
 * @author mohamednazir
 *
 */
public interface MetadataResourceProvider {
    
    public InputStream readFile(String fileName) throws Exception;
    
    public List<InputStream> readListOfFiles(String fileName);
    
    public InputStream loadResourceStateFromDatabase(String url);

    public List<String> getPreloadFileNames();

}
