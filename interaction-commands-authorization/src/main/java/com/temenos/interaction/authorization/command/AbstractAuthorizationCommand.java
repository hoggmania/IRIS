package com.temenos.interaction.authorization.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.authorization.IAuthorizationProvider;

public abstract class AbstractAuthorizationCommand {
	// Somewhere to store the bean that interfaces to a given authroization provider. Eventually this will be a resource but
	// Iris support for command calling resource does not yet exist.
	IAuthorizationProvider authorizationBean = null;
}