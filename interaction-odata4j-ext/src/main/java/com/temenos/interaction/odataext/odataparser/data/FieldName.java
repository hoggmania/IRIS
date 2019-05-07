package com.temenos.interaction.odataext.odataparser.data;

/*
 * Classes containing information about a field name (column in relational DBS).
 * 
 * For now field names are strings. One day they may be more complex.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import org.odata4j.expression.CommonExpression;
import org.odata4j.expression.EntitySimpleProperty;
import org.odata4j.expression.Expression;

import com.temenos.interaction.odataext.odataparser.ODataParser.UnsupportedQueryOperationException;

public class FieldName {

    // Wrapped OData4j object.
    private EntitySimpleProperty oData4jProperty;

    public FieldName(String name) {
        oData4jProperty = Expression.simpleProperty(name);
    }

    public FieldName(CommonExpression prop) throws UnsupportedQueryOperationException {
        if (!(prop instanceof EntitySimpleProperty)) {
            // Too complex to fit in a RowFIlter
            throw new UnsupportedQueryOperationException("Expression too complex for FieldName. Type=\"" + prop + "\"");
        }
        oData4jProperty = (EntitySimpleProperty) prop;
    }

    public FieldName(EntitySimpleProperty prop) {
        oData4jProperty = prop;
    }

    public String getName() {
        return oData4jProperty.getPropertyName();
    }

    /*
     * Get wrapped oData4J object
     */
    public EntitySimpleProperty getOData4jExpression() {
        return oData4jProperty;
    }

    /**
     * Define equality of state. To enable comparison.
     */
    @Override
    public boolean equals(Object aThat) {
        if (this == aThat)
            return true;
        if (!(aThat instanceof FieldName))
            return false;

        FieldName that = (FieldName) aThat;
        return (this.getName().equals(that.getName()));
    }

    /**
     * Return same hash code for identical objects. So contains() works.
     */
    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}