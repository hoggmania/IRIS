package com.temenos.interaction.odataext.odataparser.data;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.odataext.odataparser.ODataParser.UnsupportedQueryOperationException;

/**
 * Class to contain full user access profile information for consumers.
 * 
 * @author sjunejo
 *
 */
public class AccessProfile {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessProfile.class);

    // Filter conditions
    private RowFilters rowFilters;

    // Set of field/column to select
    private Set<FieldName> fieldNames;
    
    public AccessProfile(RowFilters rowFilters, Set<FieldName> fieldNames) {
        this.rowFilters = rowFilters;
        this.fieldNames = fieldNames;
    }
    
    @Deprecated
    public AccessProfile(List<RowFilter> rowFilters, Set<FieldName> fieldNames) {

        this.rowFilters = new RowFilters(rowFilters);
        this.fieldNames = fieldNames;
    }
    
    // TODO once old getRowFilters() has been removed rename this.
    public RowFilters getNewRowFilters() {
        return rowFilters;
    }

    @Deprecated
    public List<RowFilter> getRowFilters() {
        try {
            return rowFilters.asRowFilters();
        } catch (UnsupportedQueryOperationException e) {
            LOGGER.error("Unsupported query operation.", e);
            throw new RuntimeException("Could not convert to row filters");
        }
    }
    
    public Set<FieldName> getFieldNames() {
        return fieldNames;
    }
}
