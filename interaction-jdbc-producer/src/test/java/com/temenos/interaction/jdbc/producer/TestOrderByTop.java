package com.temenos.interaction.jdbc.producer;

/*
 * Test $orderby and $top together. These differ between servers so test each under both Oracle and MSSQL 
 * compatibility mode.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.temenos.interaction.core.MultivaluedMapImpl;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.jdbc.ServerMode;
import com.temenos.interaction.odataext.odataparser.ODataParser;

/**
 * Test $orderby options withJdbcProducer class.
 */
public class TestOrderByTop extends AbstractJdbcProducerTest {

    /**
     * Test access to database using Iris parameters with a $orderby term.
     */
    @Test
    public void testAscQueryMSSQL() {
        setMSSQLMode();
        testOrderedQuery(ServerMode.H2_MSSQL, "asc");
    }

    @Test
    public void testAscQueryOracle() {
        setOracleMode();
        testOrderedQuery(ServerMode.ORACLE, "asc");
    }

    @Test
    public void testDescQueryMSSQL() {
        setMSSQLMode();
        testOrderedQuery(ServerMode.H2_MSSQL, "desc");
    }

    @Test
    public void testDescQueryOracle() {
        setOracleMode();
        testOrderedQuery(ServerMode.ORACLE, "desc");
    }

    private void testOrderedQuery(ServerMode serverMode, String direction) {

        // Create the producer
        JdbcProducer producer = null;
        try {
            producer = new JdbcProducer(dataSource, serverMode);
        } catch (Exception e) {
            fail();
        }

        // Build up an InteractionContext
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl<String>();

        // Return all but the last row.
        queryParams.add(ODataParser.TOP_KEY, Integer.toString(TEST_ROW_COUNT - 1));

        // Order by integer.
        queryParams.add(ODataParser.ORDERBY_KEY, INTEGER_FIELD_NAME + " " + direction);

        MultivaluedMap<String, String> pathParams = new MultivaluedMapImpl<String>();
        InteractionContext ctx = new InteractionContext(mock(UriInfo.class), mock(HttpHeaders.class), pathParams,
                queryParams, mock(ResourceState.class), mock(Metadata.class));

        // Run a query
        SqlRowSet rs = null;
        try {
            rs = producer.query(TEST_TABLE_NAME, null, ctx);
        } catch (Exception e) {
            fail("Query failed: " + e);
        }

        // Check the results. Should get all fields of the single row we
        // filtered for.
        assertFalse(null == rs);

        int rowCount = 0;
        while (rs.next()) {
            int data;
            if (direction.equals("asc")) {
                data = rowCount;
            } else {
                data = TEST_ROW_COUNT - 1 - rowCount;
            }
            assertEquals(TEST_KEY_DATA + data, rs.getString(KEY_FIELD_NAME));
            assertEquals(TEST_VARCHAR_DATA + data, rs.getString(VARCHAR_FIELD_NAME));
            assertEquals(TEST_INTEGER_DATA + data, rs.getInt(INTEGER_FIELD_NAME));
            rowCount++;
        }
        assertEquals(TEST_ROW_COUNT - 1, rowCount);
    }
}
