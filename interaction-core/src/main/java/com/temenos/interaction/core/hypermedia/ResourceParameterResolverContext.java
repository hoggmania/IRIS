package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * This class is a basic container for all necessary (and future) data, required for processing @see ResourceParameterResolver
 * @author kwieconkowski
 */
public class ResourceParameterResolverContext {
    private String entityName;

    public ResourceParameterResolverContext(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}

