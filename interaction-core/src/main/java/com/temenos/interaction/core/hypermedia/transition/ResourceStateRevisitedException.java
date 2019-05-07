package com.temenos.interaction.core.hypermedia.transition;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.hypermedia.ResourceState;


/**
 * An {@link Exception} that represent the exceptional
 * scenario when a {@link ResourceState} is being used
 * more than once in a process where the uniqueness of
 * {@link ResourceState} is required for it to make sense.
 *
 * @author ikarady
 */
public class ResourceStateRevisitedException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceStateRevisitedException() {
    }

    public ResourceStateRevisitedException(String arg0) {
        super(arg0);
    }

    public ResourceStateRevisitedException(Throwable arg0) {
        super(arg0);
    }

    public ResourceStateRevisitedException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
