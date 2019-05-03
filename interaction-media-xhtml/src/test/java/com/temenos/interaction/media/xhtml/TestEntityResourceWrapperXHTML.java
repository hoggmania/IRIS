package com.temenos.interaction.media.xhtml;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.temenos.interaction.core.entity.EntityMetadata;
import com.temenos.interaction.core.entity.vocabulary.Vocabulary;
import com.temenos.interaction.core.entity.vocabulary.terms.TermComplexType;
import com.temenos.interaction.core.entity.vocabulary.terms.TermIdField;
import com.temenos.interaction.core.resource.EntityResource;

public class TestEntityResourceWrapperXHTML {

	@Test
	public void testLocalTime() {
		EntityMetadata vocs = new EntityMetadata("Test");
		Vocabulary vocName = new Vocabulary();
		vocName.setTerm(new TermComplexType(false));
		vocName.setTerm(new TermIdField(true));
		vocs.setPropertyVocabulary("time", vocName);
		
		Set<String> propertyNames = new HashSet<String>();
		propertyNames.add("time");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", new LocalTime(1, DateTimeZone.UTC));
		EntityResource<Map<String, Object>> entityResource = new EntityResource<Map<String, Object>>(map);
		
		EntityResourceWrapperXHTML er = new EntityResourceWrapperXHTML(
				vocs, propertyNames, entityResource);
		List<String> properties = er.getEntityProperties();
		assertEquals(1, properties.size());
		assertEquals("00:00:00.001", properties.get(0));
	}

}
