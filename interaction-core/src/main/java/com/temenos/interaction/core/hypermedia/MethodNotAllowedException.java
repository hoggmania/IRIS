package com.temenos.interaction.core.hypermedia;

import java.util.Collections;
import java.util.Set;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

public final class MethodNotAllowedException extends Exception {
	private final Set<String> allowedMethods;

	public MethodNotAllowedException(Set<String> allowedMethods) {
		this.allowedMethods = Collections.unmodifiableSet(allowedMethods);
	}
	
	public Set<String> getAllowedMethods() {
		return allowedMethods;
	}
}
