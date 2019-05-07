package com.temenos.interaction.loader.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.PropertiesEvent;

public class TestSequentialAction {

	@Test
	public void test() {
		List<Action> actions = new ArrayList<Action>();
		Action action1 = mock(Action.class);
		actions.add(action1);
		Action action2 = mock(Action.class);
		actions.add(action2);
		
		SequentialAction action = new SequentialAction(actions);
		PropertiesEvent event = mock(PropertiesEvent.class);
		
		action.execute(event);
		
		verify(action1).execute(event);
		verify(action2).execute(event);		
	}

}
