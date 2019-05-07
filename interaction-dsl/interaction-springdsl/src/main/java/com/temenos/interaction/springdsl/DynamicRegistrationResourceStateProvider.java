package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Resource state providers that implement this interface will provide a mechanism for dynamically registering 
 * resources i.e. at runtime after startup 
 *
 * @author mlambert
 *
 */
public interface DynamicRegistrationResourceStateProvider {
	void setStateRegisteration(StateRegisteration stateRegisteration);
}
