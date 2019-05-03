package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.InputStream;

import com.google.common.primitives.Primitives;
import com.temenos.useragent.generic.Entity;

/**
 * Defines a wrapper to the {@link Entity entity} with the possibility of
 * setting the appropriate {@link EntityHandler handler} and the current
 * {@link SessionContext session context}.
 * 
 * @author ssethupathi
 *
 */
public interface EntityWrapper extends Entity {

	void setHandler(EntityHandler handler);

	void setSessionContext(SessionContext sessionContext);

	<T> void set(String fqPropertyName, T value);

	void remove(String fqPropertyName);
	
	InputStream getContent();

    static <T> void checkValueforPrimitiveorString(T value) {
        if (null == value || !(Primitives.isWrapperType(value.getClass()) || value instanceof String)) {
            throw new IllegalArgumentException("Invalid value passed. Only string value or primitive value allowed.");
        }
    }
}
