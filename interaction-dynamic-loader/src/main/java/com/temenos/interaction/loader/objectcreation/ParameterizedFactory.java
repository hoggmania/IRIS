package com.temenos.interaction.loader.objectcreation;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * 
 * @author trojanbug
 * @param <PARAM> The class of the parameter to query the factory for the object with
 * @param <RESULT> The class produced by the factory
 */
public interface ParameterizedFactory<PARAM,RESULT> {
    public RESULT getForObject(PARAM param);    
}
