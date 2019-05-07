package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.HashMap;
import java.util.Map;


/**
 * This class holds the properties required to resolve link multivalue fields.
 */
public class LinkProperties {

    private String targetFieldFullyQualifiedName;

    private Map<String, Object> transitionProperties = new HashMap<String, Object>();

    public LinkProperties(String targetFieldName, Map<String, Object> transitionProperties) {
        this.targetFieldFullyQualifiedName = targetFieldName;
        this.transitionProperties.putAll(transitionProperties);
    }

    /**
     * @return the targetFieldFullyQualifiedName
     */
    public String getTargetFieldFullyQualifiedName() {
        return targetFieldFullyQualifiedName;
    }

    /**
     * @param targetFieldFullyQualifiedName the targetFieldFullyQualifiedName to set
     */
    public void setTargetFieldFullyQualifiedName(String targetFieldFullyQualifiedName) {
        this.targetFieldFullyQualifiedName = targetFieldFullyQualifiedName;
    }

    /**
     * @return the transitionProperties
     */
    public Map<String, Object> getTransitionProperties() {
        return transitionProperties;
    }

    /**
     * @param transitionProperties the transitionProperties to set
     */
    public void setTransitionProperties(Map<String, Object> transitionProperties) {
        this.transitionProperties = transitionProperties;
    }

}
