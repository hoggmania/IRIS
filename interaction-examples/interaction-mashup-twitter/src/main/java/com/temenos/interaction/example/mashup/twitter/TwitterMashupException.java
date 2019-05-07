package com.temenos.interaction.example.mashup.twitter;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Extension of RuntimeException to use on mashup twitter example project
 *
 * @author clopes
 *
 */
public class TwitterMashupException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Construct a new exception
     *
     * @param cause
     *            error cause
     */
    public TwitterMashupException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Construct a new exception
     *
     * @param message
     *            error message
     */
    public TwitterMashupException(String message) {
        super(message);
    }
    
    /**
     * Construct a new exception
     *
     * @param message
     *            error message
     * @param cause
     *            error cause       
     */
    public TwitterMashupException(String message, Throwable cause) {
        super(message, cause);
    }
}
