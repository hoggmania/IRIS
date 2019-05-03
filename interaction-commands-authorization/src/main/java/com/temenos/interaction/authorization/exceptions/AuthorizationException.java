package com.temenos.interaction.authorization.exceptions;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import javax.ws.rs.core.Response.StatusType;

import com.temenos.interaction.core.command.InteractionException;

public class AuthorizationException extends InteractionException {

    private static final long serialVersionUID = 1L;

    /**
     * Construct a new exception
     * 
     * @param httpStatus
     *            HTTP status code
     */
    public AuthorizationException(StatusType httpStatus) {
        super(httpStatus);
    }

    /**
     * Construct a new exception
     * 
     * @param httpStatus
     *            HTTP status code
     * @param message
     *            error message
     */
    public AuthorizationException(StatusType httpStatus, String message) {
        super(httpStatus, message);
    }

    /**
     * Construct a new exception
     * 
     * @param httpStatus
     *            HTTP status code
     * @param message
     *            error message
     * @param cause
     *            the cause. (A null value is permitted, and indicates that the
     *            cause is nonexistent or unknown.)
     */
    public AuthorizationException(StatusType httpStatus, String message, Throwable cause) {
        super(httpStatus, message, cause);
    }

    /**
     * Construct a new exception
     * 
     * @param httpStatus
     *            HTTP status code
     * @param cause
     *            the cause. (A null value is permitted, and indicates that the
     *            cause is nonexistent or unknown.)
     */
    public AuthorizationException(StatusType httpStatus, Throwable cause) {
        super(httpStatus, cause);
    }
}
