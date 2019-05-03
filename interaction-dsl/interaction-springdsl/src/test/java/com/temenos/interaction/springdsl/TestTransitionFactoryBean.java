package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.hypermedia.Transition;

/**
 * The Class TestTransitionFactoryBean.
 */
public class TestTransitionFactoryBean {

	/**
	 * Test construct.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testConstruct() throws Exception {
		ResourceState mockTargetResoruce = new ResourceState("entity", "name", null, "/test");

		Transition fromBuilder = new Transition.Builder().target(mockTargetResoruce).build();
		TransitionFactoryBean factoryBean = new TransitionFactoryBean();
		factoryBean.setTarget(mockTargetResoruce);
		Transition fromFactory = factoryBean.getObject();
		assertEquals(fromBuilder, fromFactory);
	}

}
