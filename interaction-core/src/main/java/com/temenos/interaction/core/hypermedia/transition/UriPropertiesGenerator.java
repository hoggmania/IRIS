package com.temenos.interaction.core.hypermedia.transition;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.hypermedia.HypermediaTemplateHelper;
import com.temenos.interaction.core.hypermedia.Transition;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Generates properties out of URI parameters and {@link Transition} properties.
 *
 * @author ikarady
 */
public class UriPropertiesGenerator implements PropertiesGenerator {

    private Map<String, String> uriParameters;
    private Map<String, Object> transitionProperties;

    public UriPropertiesGenerator(Map<String, String> uriParameters, Map<String, Object> transitionProperties) {
        this.uriParameters = uriParameters;
        this.transitionProperties = transitionProperties;
    }

    /**
     * Generates properties out of its URI parameters using
     * {@link Transition} properties to replace any placeholders.
     *
     * @return properties
     */
    @Override
    public Map<String, Object> generate() {
        Map<String, Object> properties = new HashMap<>();
        if (uriParameters != null) {
            for (String key : uriParameters.keySet()) {
                String value = uriParameters.get(key);
                value = HypermediaTemplateHelper.templateReplace(value, transitionProperties);
                if (!StringUtils.isEmpty(value)) {
                    properties.put(key, value);
                }
            }
        }
        return properties;
    }
}
