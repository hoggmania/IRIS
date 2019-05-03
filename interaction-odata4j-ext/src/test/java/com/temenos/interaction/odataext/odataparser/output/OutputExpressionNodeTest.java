package com.temenos.interaction.odataext.odataparser.output;


/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OutputExpressionNodeTest {
    
    @Test
    public void testGetParent() {
        OutputExpressionNode parent = new OutputExpressionNode(null);
        OutputExpressionNode child = new OutputExpressionNode(parent);
        
        assertEquals(parent, child.getParent());
    }
    
    @Test
    public void testTooManyArgumentFailure() {
        OutputExpressionNode parent = new OutputExpressionNode(null);
        
        assertTrue(parent.addArgument("a"));
        assertTrue(parent.addArgument("b"));
        assertTrue(parent.addArgument("c"));
        assertFalse(parent.addArgument("d"));
    } 
    
    @Test
    public void testMultipleSetOpFailure() {
        String op = "anOp";
        OutputExpressionNode node  = new OutputExpressionNode(null);
        
        assertTrue(node.setOp(op));
        assertFalse(node.setOp(op));
    } 
    
    @Test
    public void testToString() {
        OutputExpressionNode parent = new OutputExpressionNode(null);
        
        String op = "parentOp";
        String lhValue = "value1"; 
        String rhValue = "value2"; 
        
        // Build a small tree
        parent.addArgument(lhValue);
        parent.setOp(op);
        parent.addArgument(rhValue);
        
        assertEquals(lhValue + " " + op + " " + rhValue, parent.toOdataParameter());
    }
    
    @Test
    public void testIsBracketed() {
        OutputExpressionNode node = new OutputExpressionNode(null);
        assertFalse(node.isBracketed());
        node.setIsBracketed();
        assertTrue(node.isBracketed());
    }
    
    @Test
    public void testIsFunction() {
        OutputExpressionNode node = new OutputExpressionNode(null);
        assertFalse(node.isFunction());
        node.setIsFunction();
        assertTrue(node.isFunction());
    }
}
