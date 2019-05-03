package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * A Spring {@link ApplicationContextAware} version of {@link SpringContextBasedInteractionCommandController}, allowing for automatic injection of the ApplicationContext
 * @author trojanbug
 */
public class SpringContextAwareInteractionCommandController 
extends SpringContextBasedInteractionCommandController 
implements ApplicationContextAware {
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {            
		super.setApplicationContext(applicationContext);
	}
}