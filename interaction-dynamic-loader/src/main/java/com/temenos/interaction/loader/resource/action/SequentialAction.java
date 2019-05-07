package com.temenos.interaction.loader.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.List;

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.FileEvent;

public class SequentialAction implements Action<FileEvent> {
	List<Action> actions = new ArrayList<Action>();
	
	public SequentialAction(List<Action> actions) {
		this.actions = actions;
	}

	@Override
	public void execute(FileEvent event) {
		for (Action action : actions) {
			action.execute(event);
		}
	}
}
