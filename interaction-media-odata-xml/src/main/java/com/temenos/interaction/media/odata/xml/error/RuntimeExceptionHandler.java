package com.temenos.interaction.media.odata.xml.error;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.httpclient.HttpStatus;

import com.temenos.interaction.core.command.CommandHelper;
import com.temenos.interaction.core.entity.GenericError;
import com.temenos.interaction.core.resource.EntityResource;

/**
 * Marshals an unhandled, unchecked exception thrown by IRIS
 * into a GenericError. 
 *
 * @author dgroves
 *
 */
@Provider
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML})
public class RuntimeExceptionHandler extends InteractionExceptionHandler<RuntimeException> 
        implements ExceptionMapper<RuntimeException> {
    
    private static final Logger logger = LoggerFactory.getLogger(RuntimeExceptionHandler.class);
    
    @Override
    public Response toResponse(RuntimeException exception) {
        int code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        String stackTrace = generateLogMessage(exception, code);
        EntityResource<?> er = CommandHelper.createEntityResource(new GenericError(Integer.toString(code), 
                stackTrace), GenericError.class);
        return Response.serverError().entity(er.getGenericEntity()).build();
    }
}
