package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Implementation of {@link CommandController} delegating the command resolution
 * to underlying Spring ApplicationContext. The bean resolution is based on id
 * or name attributes of the beans in the context matching name passed
 * literally.
 *
 * @author trojanbug
 */
public class SpringContextBasedInteractionCommandController
        implements CommandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextBasedInteractionCommandController.class);

    private ApplicationContext applicationContext = null;

    /**
     * @param name
     * @return The object returned by calling getBean(name,
     * InteractionCommand.class) method on the underlying application context,
     * or null if no such bean found, or application context is not set. If the
     * bean name matches, but it is not an implementation of
     * {@link InteractionCommand} will be silently ignored.
     */
    @Override
    public InteractionCommand fetchCommand(String name) {
        if (applicationContext == null) {
            LOGGER.warn("ApplicationContext not initialised in fetchCommand of {}", this.getClass().getName());
            return null;
        }
        try {
            LOGGER.trace("{} requesting bean implementing InteractionCommand under name {} from underlying ApplicationContext", this.getClass().getName(), name);
            return applicationContext.getBean(name, InteractionCommand.class);
        } catch (BeansException ex) {
            LOGGER.trace(String.format("Could not find bean implementing interaction command under name %s", name), ex);
            return null;
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public boolean isValidCommand(String name) {
        if (applicationContext == null) {
            LOGGER.warn("applicationContext not initialised in isValidCommand of " + this.getClass());
            return false;
        }
        try {
            LOGGER.trace("{} requesting bean implementing InteractionCommand under name {} from underlying ApplicationContext", this.getClass().getName(), name);
            return (applicationContext.getBean(name, InteractionCommand.class) != null);
        } catch (BeansException ex) {
            LOGGER.trace(String.format("Could not find bean implementing interaction command under name %s", name), ex);
            return false;
        }
    }

}
