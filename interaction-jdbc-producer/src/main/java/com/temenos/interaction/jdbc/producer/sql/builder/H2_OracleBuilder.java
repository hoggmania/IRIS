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
import com.temenos.interaction.odataext.odataparser.data.AccessProfile;
import com.temenos.interaction.odataext.odataparser.data.OrderBy;

public class H2_OracleBuilder extends OracleBuilder {

    /*
     * Constructor when there is a key.
     */
    public H2_OracleBuilder(String tableName, String keyValue, AccessProfile accessProfile,
            ColumnTypesMap colTypesMap, String top, String skip, List<OrderBy> orderBy) {
        super(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
    }
    
    /*
     * Set up server compatibility modes.
     */
    @Override
    public void setCompatibilityMode() {
        this.serverMode = ServerMode.ORACLE;
        this.serverIsEmulated = true;
    }
}