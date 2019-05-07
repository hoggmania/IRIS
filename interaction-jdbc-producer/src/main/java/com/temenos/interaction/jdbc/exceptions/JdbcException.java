package com.temenos.interaction.jdbc.exceptions;

import javax.ws.rs.core.Response.StatusType;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class JdbcException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    private final transient StatusType httpStatus;	

	/**
	 * Construct a new exception
	 * 
	 * @param httpStatus
	 *            HTTP status code
	 */
	public JdbcException(StatusType httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Construct a new exception
	 * 
	 * @param httpStatus
	 *            HTTP status code
	 * @param message
	 *            error message
	 */
	public JdbcException(StatusType httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
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
	public JdbcException(StatusType httpStatus, String message, Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
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
	public JdbcException(StatusType httpStatus, Throwable cause) {
	    super(cause);
		this.httpStatus = httpStatus;
	}
	
    /**
     * Return the HTTP status code
     * @return http status code
     */
    public StatusType getHttpStatus() {
        return httpStatus;
    }	
}
