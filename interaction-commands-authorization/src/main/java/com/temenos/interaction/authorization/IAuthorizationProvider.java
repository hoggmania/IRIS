package com.temenos.interaction.authorization;

/*
 * Interface for authentication beans.
 */

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Set;

import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.odataext.odataparser.data.AccessProfile;
import com.temenos.interaction.odataext.odataparser.data.FieldName;
import com.temenos.interaction.odataext.odataparser.data.RowFilters;

public interface IAuthorizationProvider {

    /**
     * This method will return the authorized AccessProfile for a current logged
     * in user.
     * 
     * @param ctx
     */
    public AccessProfile getAccessProfile(InteractionContext ctx) throws InteractionException;

    /*
     * Get the row filter, for the current principle, in new filter format.
     * 
     * An empty list means do no filtering i.e. return all rows. This is
     * represented by a missing $filter term.
     */
    public RowFilters getFilters(InteractionContext ctx) throws InteractionException;

    /*
     * Get the select for the current principle.
     * 
     * An empty list means do no selecting i.e. return all columns. This is
     * represented by a missing $select term.
     */
    public Set<FieldName> getSelect(InteractionContext ctx) throws InteractionException;

}