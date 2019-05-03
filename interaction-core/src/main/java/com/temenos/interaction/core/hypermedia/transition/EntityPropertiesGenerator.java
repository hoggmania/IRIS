package com.temenos.interaction.core.hypermedia.transition;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.hypermedia.Transformer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Generates properties out of an entity object using a {@link Transformer}.
 *
 * @author ikarady
 */
public class EntityPropertiesGenerator implements PropertiesGenerator {

    private Transformer transformer;
    private Object entity;

    public EntityPropertiesGenerator(Transformer transformer, Object entity) {
        this.transformer = transformer;
        this.entity = entity;
    }

    /**
     * Generates properties out of its entity object.
     *
     * @return properties
     */
    @Override
    public Map<String, Object> generate() {
        Map<String, Object> properties = new HashMap<>();
        if (entity != null && transformer != null) {
            properties = transformer.transform(entity);
        }
        if (properties != null) {
            return properties;
        } else {
            return Collections.emptyMap();
        }
    }
}
