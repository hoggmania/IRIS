package com.temenos.interaction.media.hal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import javax.ws.rs.core.MediaType;

public class HALMediaType {

    /** "application/hal+xml" */
    public final static String APPLICATION_HAL_XML = "application/hal+xml";
    /** "application/hal+xml" */
    public final static MediaType APPLICATION_HAL_XML_TYPE = new MediaType("application","hal+xml");

    /** "application/hal+json" */
    public final static String APPLICATION_HAL_JSON = "application/hal+json";
    /** "application/hal+json" */
    public final static MediaType APPLICATION_HAL_JSON_TYPE = new MediaType("application","hal+json");

	public static String baseMediaType( MediaType parameterisedMediaType ) {
		return String.format("%s/%s", parameterisedMediaType.getType(), parameterisedMediaType.getSubtype());
	}
	public static String charset( MediaType parameterisedMediaType, String defaultMediaType ) {
		String specified = parameterisedMediaType.getParameters().get("charset");
		if ( specified == null )
			return defaultMediaType;
		else
			return specified;
	}
}
