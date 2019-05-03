package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;

import com.temenos.interaction.core.MultivaluedMapImpl;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.resource.EntityResource;

/**
 * Tests for NoopGETCommand implementation of InteractionCommand interface
 *
 * @author clopes
 *
 */
public class TestNoopGETCommand {

    @SuppressWarnings("unchecked")
    @Test
    public void testNoopGETCommandResourceNull() throws Exception {
        
        MultivaluedMap<String,String> map = new MultivaluedMapImpl<>();
        map.add("param1","param1value");
        map.add("param2","param2value");
        map.add("param3","param3value1");
        map.add("param3","param3value2");
        
        InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), mock(MultivaluedMap.class), map, mock(ResourceState.class), mock(Metadata.class));
        ctx.setResource(null);

        InteractionCommand mockCommand = new NoopGETCommand();
        mockCommand.execute(ctx);
        
        assertNotNull(ctx.getOutQueryParameters());
        assertTrue(ctx.getOutQueryParameters().size() == map.size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testNoopGETCommandResourceNotNull() throws Exception {
        
        MultivaluedMap<String,String> map = new MultivaluedMapImpl<>();
        map.add("param1","param1value");
        map.add("param2","param2value");
        map.add("param3","param3value1");
        map.add("param3","param3value2");
        
        InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), mock(MultivaluedMap.class), map, mock(ResourceState.class), mock(Metadata.class));
        ctx.setResource(new EntityResource<Object>());

        InteractionCommand mockCommand = new NoopGETCommand();
        mockCommand.execute(ctx);
        
        assertNotNull(ctx.getOutQueryParameters());
        assertTrue(ctx.getOutQueryParameters().size() == map.size());
    }
}
