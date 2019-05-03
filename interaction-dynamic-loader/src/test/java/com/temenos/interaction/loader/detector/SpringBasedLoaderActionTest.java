package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import com.temenos.interaction.core.command.ChainingCommandController;
import com.temenos.interaction.core.loader.FileEvent;
import com.temenos.interaction.loader.classloader.CachingParentLastURLClassloaderFactory;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class SpringBasedLoaderActionTest {

    @Test
    public void test() throws IOException, InterruptedException {
        
    	CachingParentLastURLClassloaderFactory classLoaderFactory = new CachingParentLastURLClassloaderFactory();
    	
        FileEvent<File> dirEvent = new FileEvent<File>() {
            @Override
            public File getResource() {
                return new File("src/test/jars/");
            }
        };

        ChainingCommandController chainingCommandController = new ChainingCommandController();
       
        SpringBasedLoaderAction instance = new SpringBasedLoaderAction();
        instance.setParentChainingCommandController(chainingCommandController);
        instance.setClassloaderFactory(classLoaderFactory);
        // Check chainingCommandController and try to find the GETEntities command before the loading
        // This test should be false because the bean doesn't exist
        Assert.assertFalse(chainingCommandController.isValidCommand("GETEntities"));
        instance.execute(dirEvent);
        
        // Check chainingCommandController and try to find the GETEntities command after the loading
        // This test should be true because the bean has been loaded
        Assert.assertTrue(instance.getParentChainingCommandController().isValidCommand("GETEntities"));
    }
}
