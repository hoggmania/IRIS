package com.temenos.interaction.odataext.odataparser.data;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RowFilterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstruct() {

        FieldName name = new FieldName("aname");

        RowFilter filter = new RowFilter(name, Relation.EQ, "avalue");

        assertEquals("aname", filter.getFieldName().getName());
        assertEquals(Relation.EQ, filter.getRelation());
        assertEquals("avalue", filter.getValue());
    }

    @Test
    public void testStringConstruct() {

        RowFilter filter = new RowFilter("aname", Relation.EQ, "avalue");

        assertEquals("aname", filter.getFieldName().getName());
        assertEquals(Relation.EQ, filter.getRelation());
        assertEquals("avalue", filter.getValue());
    }
}
