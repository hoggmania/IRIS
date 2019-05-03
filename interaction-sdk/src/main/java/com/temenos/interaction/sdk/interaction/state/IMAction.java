package com.temenos.interaction.sdk.interaction.state;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Set;

public interface IMAction {

	/**
	 * Indicates if a state has actions
	 * @return true or false
	 */
	public boolean hasActions();
	
	/**
	 * Returns the actions
	 * @return actions
	 */
	public Set<String> getActions();	
}
