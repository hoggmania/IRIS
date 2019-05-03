package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.Url;

/**
 * Defines a {@link Link link} which can support executing http methods on it
 * through a {@link Url url} so user agents can follow it.
 * 
 * @author ssethupathi
 *
 */
public interface ActionableLink extends Link {

	Url url();
}
