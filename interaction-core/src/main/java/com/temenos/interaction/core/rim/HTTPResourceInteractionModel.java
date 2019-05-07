package com.temenos.interaction.core.rim;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.wink.common.model.multipart.InMultiPart;

import com.temenos.interaction.core.ExtendedMediaTypes;
import com.temenos.interaction.core.media.hal.HALMediaType;
import com.temenos.interaction.core.resource.EntityResource;

public interface HTTPResourceInteractionModel extends ResourceInteractionModel {

	/**
	 * GET a resource representation.
	 */
	@GET
	@Produces({
			MediaType.APPLICATION_ATOM_XML,
			MediaType.APPLICATION_XML,
			ExtendedMediaTypes.APPLICATION_ATOMSVC_XML,
			MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XHTML_XML,
			MediaType.TEXT_HTML,
			MediaType.WILDCARD})
	public Response get(@Context HttpHeaders headers, @PathParam("id") String id, @Context UriInfo uriInfo);
	
	/**
	 * POST a resource representation from a from.
	 */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({
        MediaType.APPLICATION_ATOM_XML,
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
        MediaType.WILDCARD})
    public Response post( @Context HttpHeaders headers, @PathParam("id") String id, @Context UriInfo uriInfo, 
    		MultivaluedMap<String, String> formParams);

    /**
     * POST a multipart request i.e. multiple resource representations 
     */
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({
        MediaType.APPLICATION_ATOM_XML,
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
        MediaType.WILDCARD})
    public Response post(@Context HttpHeaders headers, @Context UriInfo uriInfo, InMultiPart inMP);
    
	/**
	 * POST a resource representation.
	 */
	@POST
	@Consumes({
		MediaType.APPLICATION_ATOM_XML,
    	MediaType.APPLICATION_XML, 
    	MediaType.APPLICATION_JSON, 
    	MediaType.WILDCARD})
	@Produces({
		MediaType.APPLICATION_ATOM_XML,
    	MediaType.APPLICATION_XML, 
    	MediaType.APPLICATION_JSON, 
    	MediaType.WILDCARD})
	public Response post(@Context HttpHeaders headers, @PathParam("id") String id, @Context UriInfo uriInfo,
			EntityResource<?> resource);

    /**
     * PUT a multipart request i.e. multiple resource representations 
     */	
    @PUT
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({
        MediaType.APPLICATION_ATOM_XML,
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
        MediaType.WILDCARD})
    public Response put(@Context HttpHeaders headers, @Context UriInfo uriInfo, InMultiPart inMP);
	
	/**
	 * PUT a resource.
	 */
	@PUT
	@Consumes({
		MediaType.APPLICATION_ATOM_XML,
    	MediaType.APPLICATION_XML, 
    	MediaType.APPLICATION_JSON, 
    	MediaType.WILDCARD})
	@Produces({
		MediaType.APPLICATION_ATOM_XML,
    	MediaType.APPLICATION_XML, 
    	MediaType.APPLICATION_JSON, 
    	MediaType.WILDCARD})
	public Response put(@Context HttpHeaders headers, @PathParam("id") String id, @Context UriInfo uriInfo,
			EntityResource<?> resource);

	/**
	 * DELETE a resource.
	 */
	@DELETE
    @Produces({
        MediaType.APPLICATION_ATOM_XML,
        MediaType.APPLICATION_XML, 
        MediaType.APPLICATION_JSON, 
        MediaType.WILDCARD})
    public Response delete(@Context HttpHeaders headers, @PathParam("id") String id, @Context UriInfo uriInfo);
	
	/**
     * DELETE a resource by sending entity back.
     */
    @DELETE
    @Consumes({
        MediaType.APPLICATION_ATOM_XML,
        MediaType.APPLICATION_XML, 
        MediaType.APPLICATION_JSON,
        HALMediaType.APPLICATION_HAL_XML,
        HALMediaType.APPLICATION_HAL_JSON})
    @Produces({
        MediaType.APPLICATION_ATOM_XML,
        MediaType.APPLICATION_XML, 
        MediaType.APPLICATION_JSON, 
        MediaType.WILDCARD})
    public Response delete(@Context HttpHeaders headers, @PathParam("id") String id, @Context UriInfo uriInfo, EntityResource<?> resource);
}