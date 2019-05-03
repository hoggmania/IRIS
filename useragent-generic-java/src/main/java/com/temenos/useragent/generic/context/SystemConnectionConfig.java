package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * This class contains the connection configuration at the system level.
 * 
 * @author ssethupathi
 *
 */
public class SystemConnectionConfig implements ConnectionConfig {

	private ConnectionConfig parentConfig;

	public SystemConnectionConfig(ConnectionConfig connectionConfig) {
		this.parentConfig = connectionConfig;
	}

	@Override
	public String getValue(String propertyName) {
		return System.getProperty(propertyName,
				parentConfig.getValue(propertyName));
	}
}
