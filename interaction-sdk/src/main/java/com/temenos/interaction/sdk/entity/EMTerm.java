package com.temenos.interaction.sdk.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * This class holds information about a vocabulary term
 */
public class EMTerm {
	String name;
	String value;

	public EMTerm(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
