package com.temenos.interaction.media.odata.xml.error;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.CommandHelper;
import com.temenos.interaction.core.entity.GenericError;
import com.temenos.interaction.core.resource.EntityResource;

import org.apache.commons.httpclient.HttpStatus;

/**
 * Marshals an unhandled, unchecked exception thrown by IRIS
 * into a GenericError. 
 *
 * @author dgroves
 *
 */
@Provider
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML})
public class WebApplicationExceptionHandler extends InteractionExceptionHandler<WebApplicationException> 
        implements ExceptionMapper<WebApplicationException> {
    
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationExceptionHandler.class);
    
    @Override
    public Response toResponse(WebApplicationException exception) {
        int code = exception.getResponse().getStatus();
        String stackTrace = generateLogMessage(exception, code);
        EntityResource<?> er = CommandHelper.createEntityResource(new GenericError(Integer.toString(code), 
                stackTrace), GenericError.class);
        return Response.status(exception.getResponse().getStatus())
            .entity(er.getGenericEntity()).build();
    }
}
