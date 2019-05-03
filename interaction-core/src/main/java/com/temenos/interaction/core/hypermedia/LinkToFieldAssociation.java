package com.temenos.interaction.core.hypermedia;

import java.util.List;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Determine if links can be generated from a particular transition.
 * If so, return a collection of transition properties used to resolve multivalue fields.
 */
public interface LinkToFieldAssociation {
    
    /**
     * Return a list of properties that is used to resolve multivalue fields.
     * The size of the list determines the number of links to generate for each underlying transition.
     * @return
     */
    List<LinkProperties> getProperties();
    
    /**
     * Determine whether the transition is supported by the implementation.
     * @return
     */
    boolean isTransitionSupported();
    
}
