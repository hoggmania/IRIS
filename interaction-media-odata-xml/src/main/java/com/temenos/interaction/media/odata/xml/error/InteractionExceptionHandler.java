package com.temenos.interaction.media.odata.xml.error;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all Interaction Framework exception handlers.
 * 
 * @author dgroves
 */
public class InteractionExceptionHandler<T extends Exception> {
    
    private final Logger logger = LoggerFactory.getLogger(InteractionExceptionHandler.class);
    
    public InteractionExceptionHandler(){}
    
    protected String generateLogMessage(T exception, int code){
        String message = new StringBuilder("HTTP ")
            .append(code)
            .append(" ")
            .append(HttpStatus.getStatusText(code))
            .toString();
            logger.error(message, exception);
            return message;
    }
}
