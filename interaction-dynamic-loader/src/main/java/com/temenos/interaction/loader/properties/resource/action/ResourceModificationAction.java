package com.temenos.interaction.loader.properties.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.PropertiesChangedEvent;
import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.core.loader.PropertiesEventVisitor;
import com.temenos.interaction.core.loader.PropertiesLoadedEvent;
import com.temenos.interaction.core.loader.PropertiesResourceModificationAction;
import com.temenos.interaction.loader.resource.action.AbstractResourceModificationAction;

public class ResourceModificationAction extends AbstractResourceModificationAction implements PropertiesResourceModificationAction {
	private Action<PropertiesChangedEvent<Resource>> changedAction;
	private Action<PropertiesLoadedEvent<Resource>> loadedAction;
	
	public void setChangedAction(Action<PropertiesChangedEvent<Resource>> changedAction) {
		this.changedAction = changedAction;
	}
	
	public void setLoadedAction(Action<PropertiesLoadedEvent<Resource>> loadedAction) {
		this.loadedAction = loadedAction;
	}
				
	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.properties.resource.action.PropertiesResourceModificationAction#notify(com.temenos.interaction.core.loader.PropertiesEvent)
	 */
	@Override
	public void notify(PropertiesEvent<Resource> event ) {
		event.accept(new PropertiesEventVisitor<Resource>() {
			
			@Override
			public void visit(PropertiesChangedEvent<Resource> event) {
				if(changedAction != null) {
					if(matches(event)) {
						changedAction.execute(event);
					}
				}
			}
						
			@Override
			public void visit(PropertiesLoadedEvent<Resource> event) {
				if(loadedAction != null) {
					if(matches(event)) {
						loadedAction.execute(event);
					}
				}								
			}
		});								
	}
}
