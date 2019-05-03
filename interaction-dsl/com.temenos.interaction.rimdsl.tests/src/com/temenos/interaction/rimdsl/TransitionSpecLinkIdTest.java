package com.temenos.interaction.rimdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.temenos.interaction.rimdsl.rim.DomainModel;
import com.temenos.interaction.rimdsl.rim.ResourceInteractionModel;
import com.temenos.interaction.rimdsl.rim.State;

@InjectWith(RIMDslInjectorProvider.class)
@RunWith(XtextRunner.class)
public class TransitionSpecLinkIdTest {
	
	@Inject 
	IGenerator underTest;
	@Inject
	ParseHelper<DomainModel> parseHelper;
	
	private final static String LINE_SEP = System.getProperty("line.separator");

	// rim with drill down
	private final static String SIMPLE_STATES_RIM_1 = "" +
			"rim Simple {" + LINE_SEP +
			"initial resource A {" + LINE_SEP +
			"	type: collection" + LINE_SEP +
			"	entity: ENTITY" + LINE_SEP +
			"	view: GetEntity" + LINE_SEP +
			"	GET *-> B {" + LINE_SEP +
			"		id: \"123456\" " + LINE_SEP +
			"	}" + LINE_SEP +
			"}" + LINE_SEP +
			"resource B {" +
			"	type: item" + LINE_SEP +
			"	entity: ENTITY" + LINE_SEP +
			"	actions [ UpdateEntity ]" + LINE_SEP +
			"}" + LINE_SEP +
			"}" + LINE_SEP +
			"";
	
	//rim without drill down
	private final static String SIMPLE_STATES_RIM_2 = "" +
			"rim Simple {" + LINE_SEP +
			"initial resource A {" + LINE_SEP +
			"	type: collection" + LINE_SEP +
			"	entity: ENTITY" + LINE_SEP +
			"	view: GetEntity" + LINE_SEP +
			"	GET *-> B " + LINE_SEP +
			"}" + LINE_SEP +
			"resource B {" +
			"	type: item" + LINE_SEP +
			"	entity: ENTITY" + LINE_SEP +
			"	actions [ UpdateEntity ]" + LINE_SEP +
			"}" + LINE_SEP +
			"}" + LINE_SEP +
			"";

	
	@Test
	public void testForTransitionSpecWithIdAvailablity() throws Exception {
		DomainModel domainModel = parseHelper.parse(SIMPLE_STATES_RIM_1);
		ResourceInteractionModel model = (ResourceInteractionModel) domainModel.getRims().get(0);
		State state = model.getStates().get(0);	
		assertNotNull(state.getTransitions().get(0).getSpec().getId());
		assertEquals("123456", state.getTransitions().get(0).getSpec().getId().getName());
	}
	
	@Test
	public void testForTransitionSpecWithoutIdUnavailablity() throws Exception {
		DomainModel domainModel = parseHelper.parse(SIMPLE_STATES_RIM_2);
		ResourceInteractionModel model = (ResourceInteractionModel) domainModel.getRims().get(0);
		assertEquals(1, model.getStates().get(0).getTransitions().size());
		assertNotNull(model.getStates().get(0).getTransitions().get(0));
		assertNull(model.getStates().get(0).getTransitions().get(0).getSpec());
	}
}


