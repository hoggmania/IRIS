package com.temenos.interaction.jdbc.producer.sql.builder;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

import com.temenos.interaction.jdbc.ServerMode;
import com.temenos.interaction.jdbc.producer.sql.ColumnTypesMap;
import com.temenos.interaction.odataext.odataparser.data.AccessProfile;
import com.temenos.interaction.odataext.odataparser.data.OrderBy;

/**
 * Implementation to build SQL Statement for MS SQL Server
 *
 * @author sjunejo
 *
 */
public class H2_MSSQLBuilder extends MSSQLBuilder {
    
    /**
     * Constructor to build a SQL Server Command Builder
     */
    public H2_MSSQLBuilder(String tableName, String keyValue, AccessProfile accessProfile, ColumnTypesMap colTypesMap,
            String top, String skip, List<OrderBy> orderBy) {
        super(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
    }

    @Override
    public void setCompatibilityMode() {
        this.serverMode = ServerMode.MSSQL;
        this.serverIsEmulated = true;
    };
}