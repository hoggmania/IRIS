package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-contexts/interaction-command-test-context.xml"})
public class TestSpringContextAwareInteractionCommandController {

    @Autowired
    SpringContextAwareInteractionCommandController commandController;

    @Test
    public void testFetchCommandWorksForConfiguredCommands() throws BeansException {
        Assert.assertNotNull(commandController.fetchCommand("testCommand1"));
        Assert.assertNotNull(commandController.fetchCommand("testCommand2"));
        Assert.assertTrue(commandController.isValidCommand("testCommand1"));
        Assert.assertTrue(commandController.isValidCommand("testCommand2"));
        Assert.assertEquals(TestCommand.class, commandController.fetchCommand("testCommand1").getClass());
    }

    @Test
    public void testFetchCommandDoesNotReturnAnythingElse() throws BeansException {
        // we don't expect any exceptions, just null returned
        Assert.assertNull(commandController.fetchCommand("testCommandNameNotConfiguredAnywhere"));
        Assert.assertFalse(commandController.isValidCommand("testCommandNameNotConfiguredAnywhere"));
    }
    
        @Test
    public void testScopesAreWorking() throws BeansException {
            InteractionCommand ic1_1 = commandController.fetchCommand("testCommand1");
            InteractionCommand ic1_2 = commandController.fetchCommand("testCommand1");

            Assert.assertTrue(ic1_1 == ic1_2);

            InteractionCommand ic3_1 = commandController.fetchCommand("testCommand3");
            InteractionCommand ic3_2 = commandController.fetchCommand("testCommand3");

            Assert.assertTrue(ic3_1 != ic3_2);
    }
}
