package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

/**
 * This class contains base connection configuration parameters.
 * 
 * @author ssethupathi
 *
 */
public class BaseConnectionConfig implements ConnectionConfig {

	private Properties connectionProperties = new Properties();

	public BaseConnectionConfig() {
		this.connectionProperties = getBaseConnectionProperties();
	}

	@Override
	public String getValue(String name) {
		return connectionProperties.getProperty(name, "");
	}

	private Properties getBaseConnectionProperties() {
		Properties baseConnprops = new Properties();
		baseConnprops
				.setProperty(ConnectionConfig.ENDPOINT_URI,
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc");
		baseConnprops.setProperty(ConnectionConfig.SERVICE_ROOT, "GB0010001");
		baseConnprops.setProperty(ConnectionConfig.USER_NAME, "INPUTT");
		baseConnprops.setProperty(ConnectionConfig.PASS_WORD, Base64.decodeBase64("MTIzNDU2").toString());
		return baseConnprops;
	}
}
