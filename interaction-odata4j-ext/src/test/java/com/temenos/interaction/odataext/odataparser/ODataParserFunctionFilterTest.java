package com.temenos.interaction.odataext.odataparser;

/*
 * Test class for the oData unary function parser/printer filter operations.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.temenos.interaction.odataext.odataparser.data.Relation;
import com.temenos.interaction.odataext.odataparser.output.OutputExpressionVisitor;

public class ODataParserFunctionFilterTest extends AbstractODataParserFilterTest {

    @Test
    public void testUnaaryFunctionsFilter() {
        for (Relation rel : Relation.values()) {
            if (rel.isFunctionCall() && (1 == rel.getExpectedArgumentCount())) {
                if (Relation.ISOF != rel) {
                    testValid("a eq " + rel.getoDataString() + "(a)");
                } else {
                    // ISOF requires literal arguments.
                    testValid("a eq " + rel.getoDataString() + "('a')");
                }
            }
        }
    }

    @Test
    public void testBinaryFunctionsFilter() {
        for (Relation rel : Relation.values()) {
            if (rel.isFunctionCall() && (2 == rel.getExpectedArgumentCount())) {
                testValid("a eq " + rel.getoDataString() + "(a, b)");
            }
        }
    }

    /*
     * Test the few ternary operators
     */
    @Test
    public void testTernaryFunctionsFilter() {
        // Test the 3 argument version of substring
        testValid("a eq substring('a', 'b', 'c')");

        for (Relation rel : Relation.values()) {
            if (rel.isFunctionCall() && (3 == rel.getExpectedArgumentCount())) {
                testValid("a eq " + rel.getoDataString() + "(a, b, c)");
            }
        }
    }

    /*
     * Test a few special function.
     */
    @Test
    public void testSpecialFunctionsFilter() {
        // Substr can also be called with 3 args
        testValid("a eq " + Relation.SUBSTR.getoDataString() + "(a, b, c)");
    }

    /*
     * Test the visitor passing interface.
     */
    @Test
    public void testVisitorFunctionsFilter() {
        String expected = "a eq " + Relation.SUBSTR.getoDataString() + "(a, b, c)";
        String actual = ODataParser.toFilters(ODataParser.parseFilters(expected), new OutputExpressionVisitor());
        assertEquals(expected, actual);
    }
}
