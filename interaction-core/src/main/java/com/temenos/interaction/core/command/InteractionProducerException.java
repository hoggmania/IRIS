package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.Response.StatusType;

import org.odata4j.core.OError;
import org.odata4j.exceptions.ODataProducerException;

import com.temenos.interaction.core.resource.EntityResource;

/**
 * Interaction Producer exception.
 * This exception enables IRIS Producer to raise interaction producer error messages.  
 */
public class InteractionProducerException extends ODataProducerException {	
	private static final long serialVersionUID = 1L;
	private final String entitySetName;
	private final transient EntityResource<?> entityResource;
	private final transient StatusType httpStatus;

	/**
	 * @param error
	 */
	public InteractionProducerException(StatusType httpStatus, OError error, String entitySetName, EntityResource<?> entityResource) {
		super(error);
		this.httpStatus = httpStatus;
		this.entitySetName = entitySetName;
		this.entityResource = entityResource;
	}
	
	public InteractionProducerException(StatusType httpStatus, String message, String entitySetName, EntityResource<?> entityResource) {
		super(message, null);
		this.httpStatus = httpStatus;
		this.entitySetName = entitySetName;
		this.entityResource = entityResource;
	}
	
	public InteractionProducerException(StatusType httpStatus, String message, String entitySetName, EntityResource<?> entityResource, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.entitySetName = entitySetName;
        this.entityResource = entityResource;
    }

	@Override
	public StatusType getHttpStatus() {
		return httpStatus;
	}
	
	/**
	 * @return the entitySetName
	 */
	public String getEntitySetName() {
		return entitySetName;
	}

	
	/**
	 * @return the entityProperties
	 */
	public EntityResource<?> getEntityResource() {
		return entityResource; 
	}
}
