package com.temenos.interaction.odataext.odataparser.data;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odata4j.expression.OrderByExpression.Direction;

public class OrderByTest {

	@Test
	public void testConstructAscending() {

		OrderBy orderBy = new OrderBy("aname", Direction.ASCENDING);

		assertEquals("aname", orderBy.getFieldName().getName());
		assertEquals(Direction.ASCENDING, orderBy.getDirection());
        assertTrue(orderBy.isAcsending());
        assertEquals("asc", orderBy.getDirectionString());
	}
	
	@Test
    public void testConstructDescending() {
        OrderBy orderBy = new OrderBy("aname", Direction.DESCENDING);

        assertEquals("aname", orderBy.getFieldName().getName());
        assertEquals(Direction.DESCENDING, orderBy.getDirection());
        assertFalse(orderBy.isAcsending());
        assertEquals("desc", orderBy.getDirectionString());
    }
	
	@Test
	public void testEquivalence() {
		OrderBy orderBy1 = new OrderBy("aname", Direction.DESCENDING);
		OrderBy orderBy2 = new OrderBy("aname", Direction.DESCENDING);
		OrderBy orderBy3 = new OrderBy("aothername", Direction.DESCENDING);
        OrderBy orderBy4 = new OrderBy("aname", Direction.ASCENDING);

		assertEquals(orderBy1, orderBy2);
		assertFalse(orderBy1.equals(orderBy3));
        assertFalse(orderBy1.equals(orderBy4));
	}
	
	@Test
	public void testHashEquivalence() {
        OrderBy orderBy1 = new OrderBy("aname", Direction.DESCENDING);
        OrderBy orderBy2 = new OrderBy("aname", Direction.DESCENDING);
        OrderBy orderBy3 = new OrderBy("aothername", Direction.DESCENDING);
        OrderBy orderBy4 = new OrderBy("aname", Direction.ASCENDING);

		assertTrue(orderBy1.hashCode() == orderBy2.hashCode());
		assertFalse(orderBy1.hashCode() == orderBy3.hashCode());
        assertFalse(orderBy1.hashCode() == orderBy4.hashCode());
	}
}
