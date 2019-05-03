package com.temenos.interaction.core.media.xml.jaxb;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XMLEntity {

	@XmlAttribute
	public String name; 

	@XmlElement
	public Map<String, XMLEntityProperty> properties = new HashMap<String, XMLEntityProperty>();
}
