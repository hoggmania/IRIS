package com.temenos.interaction.jdbc.producer.sql;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.temenos.interaction.jdbc.ServerMode;
import com.temenos.interaction.jdbc.exceptions.JdbcException;
import com.temenos.interaction.jdbc.producer.sql.builder.H2_MSSQLBuilder;
import com.temenos.interaction.jdbc.producer.sql.builder.H2_OracleBuilder;
import com.temenos.interaction.jdbc.producer.sql.builder.MSSQLBuilder;
import com.temenos.interaction.jdbc.producer.sql.builder.OracleBuilder;
import com.temenos.interaction.odataext.odataparser.data.AccessProfile;
import com.temenos.interaction.odataext.odataparser.data.OrderBy;

/**
 * Factory to return appropriate SqlBuilder implementation according to target server type in use 
 *
 * @author sjunejo
 *
 */
public final class SqlBuilderFactory {
    
    private SqlBuilderFactory() {
    }

    public static SqlBuilder getSqlBuilder(String tableName, String keyValue, AccessProfile accessProfile, ColumnTypesMap colTypesMap, String top,
            String skip, List<OrderBy> orderBy, ServerMode serverMode) throws JdbcException {
        SqlBuilder builder = null;
        if (serverMode != null) {
            switch (serverMode) {
            case MSSQL:
                builder = new MSSQLBuilder(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
                break;
            case H2_MSSQL:
                builder = new H2_MSSQLBuilder(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
                break;
            case ORACLE:
                builder = new OracleBuilder(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
                break;
            case H2_ORACLE:
                builder = new H2_OracleBuilder(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
                break;
            default:
                throw new JdbcException(Status.PRECONDITION_FAILED, "DB Server \"" + serverMode.toString() + "\" not supported");
            }
        } else {
            // Defaults to Emulated MSSQL
            builder = new H2_MSSQLBuilder(tableName, keyValue, accessProfile, colTypesMap, top, skip, orderBy);
        }
        return builder;
    }
}
