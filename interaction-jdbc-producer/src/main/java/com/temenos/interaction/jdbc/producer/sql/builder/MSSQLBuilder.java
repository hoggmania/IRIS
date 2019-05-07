package com.temenos.interaction.jdbc.producer.sql.builder;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.jdbc.ServerMode;
import com.temenos.interaction.jdbc.producer.sql.ColumnTypesMap;
import com.temenos.interaction.jdbc.producer.sql.SqlBuilder;
import com.temenos.interaction.odataext.odataparser.data.AccessProfile;
import com.temenos.interaction.odataext.odataparser.data.OrderBy;

/**
 * Implementation to build SQL Statement for MS SQL Server
 *
 * @author sjunejo
 *
 */
public class MSSQLBuilder extends SqlBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MSSQLBuilder.class);

    /**
     * Constructor to build a SQL Server Command Builder
     */
    public MSSQLBuilder(String tableName, String keyValue, AccessProfile accessProfile, ColumnTypesMap colTypesMap,
            String top, String skip, List<OrderBy> orderBy) {
        super(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
    }

    @Override
    public void setCompatibilityMode() {
        this.serverMode = ServerMode.MSSQL;
        this.serverIsEmulated = false;
    };

    /*
     *  Build SQL Server command     
     */
    @Override
    public String getCommand() {
        StringBuilder builder = new StringBuilder("SELECT");
        addSelects(builder);
        addFromTerm(builder);
        addWhereTerms(builder);
        addOrderByTerms(builder);
        addTopAndSkip(builder);
        
        // Package the inner SQL command in an outer SQL command.
        return builder.toString();
    }

    /*
     * Add $top and $skip components for this server type.
     * 
     * This is messy. To support pagination an inner select is wrapped by an
     * outer select. For more information search online for "oracle pagination".
     */
    @Override
    protected void addTopAndSkip(StringBuilder builder) {
        if ((null == top) && (null == skip)) {
            // Nothing to do
            return;
        }

        // Add where clauses
        addSkip(builder);
        addTop(builder);
    }

    private void addSkip(StringBuilder builder) {
        int skipAsInt = 0;
        try {
            skipAsInt = skip == null ? SKIP_ROWS_DEFAULT : Integer.parseInt(skip);
        } catch (NumberFormatException nfe) {
            skipAsInt = SKIP_ROWS_DEFAULT;
            LOGGER.warn("Invalid value provided to skip rows", nfe);
        }
        builder.append(" OFFSET " + skipAsInt + " ROWS");
    }

    private void addTop(StringBuilder builder) {
        int maxRow = 0;
        try {
            // Work out max row
            maxRow = top == null ? MAX_ROWS_DEFAULT : Integer.parseInt(top);
        } catch (NumberFormatException nfe) {
            maxRow = MAX_ROWS_DEFAULT;
            LOGGER.warn("Invalid value provided to fetch top rows", nfe);
        }
        builder.append(" FETCH NEXT " + maxRow + " ROWS ONLY");
    }
}