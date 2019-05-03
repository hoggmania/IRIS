package com.temenos.interaction.jdbc;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 * Define type of SQL Server we support
 *
 * @author sjunejo
 *
 */
public enum ServerMode {
    // Real server modes
    MSSQL, ORACLE,
    // H2 server compatibility modes. Used for testing.
    H2_MSSQL, H2_ORACLE
}
