package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.hypermedia.Transition;
import com.temenos.interaction.core.resource.ConfigLoader;


/**
 * TODO: Document me!
 *
 * @author kprasanth
 *
 */
public class TestLoadTimestampResourceStateFiles {

    private SpringDSLResourceStateProvider dsl;
    private ConfigLoader configLoader = new ConfigLoader();
    private ResourceState resource;
    private ClassLoader classloader = this.getClass().getClassLoader();
    private ResourceLoader resourceLoader= new FileSystemResourceStateLoader();

    @Before
    public void setUpClass() {
        dsl = new SpringDSLResourceStateProvider();
        dsl.setResourceLoader(resourceLoader);
        String location = new File(classloader.getResource("PRDFiles").getPath()).getAbsolutePath();
        resourceLoader.setIrisConfigDirPath(location);
        dsl.setConfigLoader(configLoader);
        resource = dsl.getResourceState("Tst_Twins-notes");
    }

    @Test
    public void testSameResourceNameTransitionList() {

        assertNotNull(resource);
        List<Transition> transistionList = resource.getTransitions();
        assertEquals(2, transistionList.size());
        assertEquals(transistionList.get(0).getSource().toString(), "Note.notes");
        assertEquals(transistionList.get(1).getTarget().toString(), ".Tst_Twins-Page2");
    }

    @Test
    public void testInvalidResourceState() {
        ResourceState resource = dsl.getResourceState("Tst_Invalid-notes");
        assertNull(resource);
    }

    @Test
    public void testGetResourceFromClassPath() {
        dsl.getResourceState("Tst_Twins-notes");
        assertNotNull(resource);
        List<Transition> transistionList = resource.getTransitions();
        assertEquals(2, transistionList.size());
        assertEquals(transistionList.get(0).getSource().toString(), "Note.notes");
        assertEquals(transistionList.get(0).getTarget().toString(), ".Tst_Twins-Page1");
    }
}
