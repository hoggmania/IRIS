package com.temenos.interaction.jdbc.producer.sql.builder;

/*
 * Utility class for building SQL commands.
 * 
 * If given a key constructs a command for a single row. 
 * 
 * If given a null key constructs a command to add all rows.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

import com.temenos.interaction.jdbc.ServerMode;
import com.temenos.interaction.jdbc.producer.sql.ColumnTypesMap;
import com.temenos.interaction.jdbc.producer.sql.SqlBuilder;
import com.temenos.interaction.odataext.odataparser.data.AccessProfile;
import com.temenos.interaction.odataext.odataparser.data.OrderBy;

public class OracleBuilder extends SqlBuilder {
    
    /*
     * Constructor when there is a key.
     */
    public OracleBuilder(String tableName, String keyValue, AccessProfile accessProfile,
            ColumnTypesMap colTypesMap, String top, String skip, List<OrderBy> orderBy) {
        super(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
    }
    
    /*
     * Set up server compatibility modes.
     */
    @Override
    public void setCompatibilityMode() {
        this.serverMode = ServerMode.ORACLE;
        this.serverIsEmulated = false;
    }

    /*
     * Method to build Sql command
     */
    @Override
    public String getCommand() {

        // Build inner SQL command
        StringBuilder builder = new StringBuilder("SELECT");
        addSelects(builder);
        addFromTerm(builder);
        addWhereTerms(builder);
        addOrderByTerms(builder);

        // Package the inner SQL command in an outer SQL command.
        addTopAndSkip(builder);

        return builder.toString();
    }
}
