package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.Response.StatusType;

/**
 * Interaction exception.
 * This exception enables IRIS commands to raise interaction exceptions.  
 */
public class InteractionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final transient StatusType httpStatus;

	/**
	 * Construct a new exception
	 * @param httpStatus HTTP status code
	 */
	public InteractionException(StatusType httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Construct a new exception
	 * @param httpStatus HTTP status code
	 * @param message error message
	 */
	public InteractionException(StatusType httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	/**
	 * Construct a new exception
	 * @param httpStatus HTTP status code
	 * @param message error message
	 * @param cause	the cause. (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public InteractionException(StatusType httpStatus, String message, Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}


	/**
	 * Construct a new exception
	 * @param httpStatus HTTP status code
	 * @param cause	the cause. (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public InteractionException(StatusType httpStatus, Throwable cause) {
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
