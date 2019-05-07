package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Properties;

/**
 * This class contains the connection configuration at the session level.
 * 
 * @author ssethupathi
 *
 */
public class SessionConnectionConfig implements ConnectionConfig {
	private ConnectionConfig parentConfig;
	private Properties sessionProperties = new Properties();

	public SessionConnectionConfig(ConnectionConfig connectionConfig) {
		this.parentConfig = connectionConfig;
	}

	public void setValue(String propertyName, String value) {
		sessionProperties.put(propertyName, value);
	}

	@Override
	public String getValue(String propertyName) {
		return sessionProperties.getProperty(propertyName,
				parentConfig.getValue(propertyName));
	}
}
