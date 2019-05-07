package com.temenos.interaction.odataext.odataparser.data;

import java.util.Objects;

import org.odata4j.expression.Expression;
import org.odata4j.expression.OrderByExpression;
import org.odata4j.expression.OrderByExpression.Direction;

import com.temenos.interaction.odataext.odataparser.output.ParameterPrinter;

/*
 * Thin wrapper round the odat4j OrderByExpression.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

public class OrderBy {
    // Wrapped OData4j object.
    private OrderByExpression oData4jExpression;

    public OrderBy(String orderBy, Direction direction) {
        this(Expression.orderBy(Expression.parse(orderBy), direction));
    }

    public OrderBy(OrderByExpression orderBy) {
        oData4jExpression = orderBy;
    }

    public FieldName getFieldName() {
        StringBuffer sb = new StringBuffer();
        ParameterPrinter printer = new ParameterPrinter();
        printer.appendParameter(sb, oData4jExpression.getExpression(), true);
        return new FieldName(sb.toString());
    }

    public OrderByExpression getOData4jExpression() {
        return oData4jExpression;
    }

    public boolean isAcsending() {
        return oData4jExpression.getDirection() == Direction.ASCENDING;
    }

    public Direction getDirection() {
        return oData4jExpression.getDirection();
    }

    public String getDirectionString() {
        if (getDirection() == Direction.ASCENDING) {
            return "asc";
        } else {
            return "desc";
        }
    }

    /**
     * Define equality of state. To enable comparison.
     */
    @Override
    public boolean equals(Object aThat) {
        if (this == aThat)
            return true;
        if (!(aThat instanceof OrderBy))
            return false;

        OrderBy that = (OrderBy) aThat;
        return (this.getFieldName().getName().equals(that.getFieldName().getName()) && (this.getDirection() == that
                .getDirection()));
    }

    /**
     * Return same hash code for identical objects. So contains() works.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getFieldName().getName(), this.getDirection());
    }
}
