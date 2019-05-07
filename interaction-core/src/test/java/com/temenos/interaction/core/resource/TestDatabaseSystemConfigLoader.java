package com.temenos.interaction.core.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.temenos.interaction.metadata.resource.MetadataResourceProvider;

@RunWith(MockitoJUnitRunner.class)
public class TestDatabaseSystemConfigLoader {
    
    DatabaseSystemConfigLoader confLoader = new DatabaseSystemConfigLoader();
    
    MetadataResourceProvider metadataResourceProvider = Mockito.mock(MetadataResourceProvider.class);
    
    InputStream inputStream = null;
  
    @Before
    public void setUp() throws Exception {
        inputStream = this.getClass().getClassLoader().getResourceAsStream("metadata-CountryList.xml");
       
    }
    
    @Test
    public void testLoad() throws Exception {
        
        confLoader.setMetadataResourceProvider(metadataResourceProvider);
        Mockito.when(metadataResourceProvider.readFile(Mockito.anyString())).thenReturn(inputStream);
        InputStream in = confLoader.load("metadata-CountryList.xml");
        assertNotNull(in);
    }
    
}
