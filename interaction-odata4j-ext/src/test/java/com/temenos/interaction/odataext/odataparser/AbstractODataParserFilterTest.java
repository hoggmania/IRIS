package com.temenos.interaction.odataext.odataparser;

/*
 * Base test class for the oData parser/printer filter operations.
 * 
 * Not too concerned with the intermediate format of data but it must survive the 'round trip' into intermediate format
 * and back to a string.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractODataParserFilterTest {

    // Test round trip for a valid filter
    protected void testValid(String expected) {

        String actual = null;
        boolean threw = false;
        try {
            actual = ODataParser.toFilters(ODataParser.parseFilters(expected));
        } catch (Exception e) {
            threw = true;
        }

        assertFalse(threw);
        assertEquals(expected, actual);
    }

    // Test round trip for a valid old style filter
    @Deprecated
    protected void testOldValid(String expected) {

        String actual = null;
        boolean threw = false;
        try {
            actual = ODataParser.toFilter(ODataParser.parseFilter(expected));
        } catch (Exception e) {
            threw = true;
        }

        assertFalse(threw);
        assertEquals(expected, actual);
    }

    // Test invalid filter throws
    protected void testInvalid(String expected) {
        String actual = null;
        boolean threw = false;
        try {
            actual = ODataParser.toFilters(ODataParser.parseFilters(expected));
        } catch (Exception e) {
            threw = true;
        }
        assertTrue("Didn't throw. Expected \"" + expected + "\"Actual is \"" + actual + "\"", threw);
    }
}
