package com.temenos.interaction.core.rim;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.temenos.interaction.core.hypermedia.Link;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.resource.RESTResource;


/**
 * Wraps an internal IRIS Response object with a link to the resolved location
 * and any request parameters resolved in this process.
 *
 * @author dgroves
 *
 */
public final class ResponseWrapper {
    private final Response response;
    private final Link selfLink;
    private final MultivaluedMap<String, String> requestParameters;
    private final ResourceState resolvedState;
    
    public ResponseWrapper(Response response, Link selfLink,
            MultivaluedMap<String, String> requestParameters, ResourceState resolvedState) {
        this.response = response;
        this.selfLink = selfLink;
        this.requestParameters = requestParameters;
        this.resolvedState = resolvedState;
    }
    
    /**
     * Obtain the response object stored in this wrapper.
     * @return The response object associated with this wrapper.
     */
    public Response getResponse(){
        return response;
    }
    
    /**
     * Obtain a link to the fully resolved resource from this wrapper.
     * @return The Link object associated with this wrapper.
     */
    public Link getSelfLink(){
        return selfLink;
    }
    
    /**
     * Obtain query parameters resolved during internal request processing. 
     * @return A MultivaluedMap object with query parameters associated
     * to this internal request. 
     */
    public MultivaluedMap<String, String> getRequestParameters(){
        return requestParameters;
    }
    
    /**
     * Obtain the ResourceState that was resolved during the request.
     * @return The ResourceState object associated with this wrapper.
     */
    public ResourceState getResolvedState(){
        return resolvedState;
    }

    /**
     * Obtain the REST resource object of the response object stored in this wrapper.
     * @return The REST resource object of the response object stored in this wrapper.
     */
    public RESTResource getRESTResource() {
        if (response.getEntity() == null) {
            return null;
        }
        return (RESTResource) ((GenericEntity<?>) response.getEntity()).getEntity();
    }
}
