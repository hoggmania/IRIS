package com.temenos.interaction.core.hypermedia.transition;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Map;


/**
 * Defines generators that can create properties out of various objects.
 *
 * @author ikarady
 */
public interface PropertiesGenerator {

    public Map<String, Object> generate();

}
