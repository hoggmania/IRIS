package com.temenos.interaction.core.loader;



/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



public interface Action<T> {
	void execute(T event);
}
