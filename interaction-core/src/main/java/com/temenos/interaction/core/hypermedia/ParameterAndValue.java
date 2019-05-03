package com.temenos.interaction.core.hypermedia;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MultivaluedMap;

import com.temenos.interaction.core.MultivaluedMapImpl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class ParameterAndValue {
	private String parameter;
	private String value;
		
	public ParameterAndValue(String parameter, String value) {
		super();
		this.parameter = parameter;
		this.value = value;
	}
	public String getParameter() {
		return parameter;
	}
	public String getValue() {
		return value;
	}
	
	/**
     * This method would convert ParameterAndvalue[] to MultiValueMap<String, String>
     * @param paramAndValues
     * @return
     */
    public static MultivaluedMap<String, String> getParamAndValueAsMultiValueMap(ParameterAndValue[] paramAndValues) {
    	MultivaluedMap<String, String> parameters = new MultivaluedMapImpl<String>();
    	if (paramAndValues != null) {
	    	for (ParameterAndValue paramAndValue : paramAndValues) {
	    		parameters.put(paramAndValue.getParameter(), 
	    				new ArrayList<String> (Arrays.asList(paramAndValue.getValue())));
	    	}
    	}
    	return parameters;
    }
}
