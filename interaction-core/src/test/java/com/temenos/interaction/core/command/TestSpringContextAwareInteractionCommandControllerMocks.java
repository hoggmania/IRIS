package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class TestSpringContextAwareInteractionCommandControllerMocks {

    @InjectMocks
    private SpringContextAwareInteractionCommandController commandController;
    
    @Mock
    private ApplicationContext context;
    
    @Test
    public void testFetchCommandApplicationContextIsNull(){
    	commandController.setApplicationContext(null);
    	assertNull(commandController.fetchCommand("testCommand1"));
    	assertFalse(commandController.isValidCommand("testCommand1"));
    }
}
