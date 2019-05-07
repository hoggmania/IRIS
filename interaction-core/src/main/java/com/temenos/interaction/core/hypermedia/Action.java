package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Properties;

public class Action {

	public enum TYPE {
		VIEW,
		ENTRY
	}
	
	private final String name;
	private final TYPE type;
	private Properties properties;
	private String method;
	private int maxage;
	
	public Action(String name, TYPE type) {
		this.name = name;
		this.type = type;
		this.properties = new Properties();		// We should initialize to avoid NullPointerException
	}

	public Action(String name, TYPE type, Properties props) {
		this.name = name;
		this.type = type;
		this.properties = props;
	}

	public Action(String name, TYPE type, Properties props, String method) {
		this.name = name;
		this.type = type;
		this.properties = props;
		this.method = method;
	}

	public String getName() {
		return name;
	}
	
	public TYPE getType() {
		return type;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMaxage( int a ) {
		maxage = a;
	}
	
	public int getMaxage() {
		return maxage;
	}
	
	public String toString() {
		return "Action(name=\"" + name + "\", type=\"" + type + "\", method=\"" + method + "\")";
	}
}
