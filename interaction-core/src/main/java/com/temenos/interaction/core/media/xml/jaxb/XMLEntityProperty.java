package com.temenos.interaction.core.media.xml.jaxb;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class XMLEntityProperty {

	@XmlAttribute
	public String name; 
	 
	@XmlValue
	public String value;
}
