package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class TestBean {
	TestBean(String s, int i) {
		stringField = s;
		intField = i;
	}
	private String stringField;
	private int intField;
	public String getStringField() { return stringField; }
	public int getIntField() { return intField; }
}