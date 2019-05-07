package com.temenos.interaction.jdbc.producer.sql;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.temenos.interaction.jdbc.producer.AbstractJdbcProducerTest;
import com.temenos.interaction.jdbc.producer.JdbcProducer;

/**
 * Test ColumnTypesMap class.
 */
public class TestColumnTypesMap extends AbstractJdbcProducerTest {

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor() {

		// Create the producer
		JdbcProducer producer = null;
		try {
			producer = new JdbcProducer(dataSource);
		} catch (Exception e) {
			fail();
		}

		// Create the object under test
		try {
			new ColumnTypesMap(producer, TEST_TABLE_NAME, true);
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * Test getting the primary key name
	 */
	@Test
	public void testGetPrimaryKeyName() {

		// Create the producer
		JdbcProducer producer = null;
		try {
			producer = new JdbcProducer(dataSource);
		} catch (Exception e) {
			fail();
		}

		// Create the object under test
		ColumnTypesMap map = null;
		try {
			map = new ColumnTypesMap(producer, TEST_TABLE_NAME, true);
		} catch (Exception e) {
			fail();
		}

		assertEquals(KEY_FIELD_NAME, map.getPrimaryKeyName());
	}
	
	/**
	 * Test getting the default "RECID" primary key.
	 */
	@Test
	public void testGetPrimaryKeyNameDefaultKey() {
	    //drop default table created
	    dropTestTable();

		// Set up a queryable environment.
		populateTestTable(TEST_ROW_COUNT, false);

		// Create the producer
		JdbcProducer producer = null;
		try {
			producer = new JdbcProducer(dataSource);
		} catch (Exception e) {
			fail();
		}

		// Create the object under test
		ColumnTypesMap map = null;
		try {
			map = new ColumnTypesMap(producer, TEST_TABLE_NAME, true);
		} catch (Exception e) {
			fail();
		}
		
		// Since the RECID column is present should get the default key.
		assertEquals("RECID", map.getPrimaryKeyName());
	}
	
	@Test
	public void testGetType() {

		// Build up some column metadata matching the above columns. The correct
		// fields must be numeric of textual.
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("col1", java.sql.Types.VARCHAR);
		map.put("col2", java.sql.Types.INTEGER);
		ColumnTypesMap columnTypesMap = new ColumnTypesMap(map, null);

		for (String colName : map.keySet()) {
			assertEquals(map.get(colName), columnTypesMap.getType(colName));
		}
	}

	@Test
	public void testIsNueric() {

		// Build up some column metadata matching the above columns. The correct
		// fields must be numeric of textual.
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("col1", java.sql.Types.VARCHAR);
		map.put("col2", java.sql.Types.INTEGER);
		ColumnTypesMap columnTypesMap = new ColumnTypesMap(map, null);

		assertFalse(columnTypesMap.isNumeric("col1"));
		assertTrue(columnTypesMap.isNumeric("col2"));
	}

}
