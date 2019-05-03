package com.temenos.interaction.odataext.odataparser.data;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FieldNameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testConstruct() {

		FieldName name = new FieldName("aname");

		assertEquals("aname", name.getName());
	}
	
	@Test
	public void testEquivalence() {

		FieldName name1 = new FieldName("aname");
		FieldName name2 = new FieldName("aname");
		FieldName name3 = new FieldName("aothername");

		assertEquals(name1, name2);
		assertFalse(name1.equals(name3));
	}
	
	@Test
	public void testHashEquivalence() {

		FieldName name1 = new FieldName("aname");
		FieldName name2 = new FieldName("aname");
		FieldName name3 = new FieldName("aothername");

		assertTrue(name1.hashCode() == name2.hashCode());
		assertFalse(name1.hashCode() == name3.hashCode());
	}
}
