package com.temenos.interaction.loader.xml.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.FileEvent;
import com.temenos.interaction.loader.resource.action.AbstractResourceModificationAction;

public class ResourceModificationAction extends AbstractResourceModificationAction {
	private Action<FileEvent<Resource>> changedAction;
		
	public void setChangedAction(Action<FileEvent<Resource>> changedAction) {
		this.changedAction = changedAction;
	}
	
	public void notify(FileEvent<Resource> event ) {
		if(matches(event)) {
			changedAction.execute(event);
		}
	}
}
