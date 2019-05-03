package com.temenos.interaction.media.odata.xml.atomsvc;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.temenos.interaction.core.hypermedia.ResourceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.media.odata.xml.atom.AtomXMLProvider;

/**
 * A simple class just to be able to provide the baseuri to AtomServiceDocumentFormatWriter
 * @author aphethean
 */
public class ExUriInfo implements UriInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtomXMLProvider.class);
	private ResourceState serviceDocument;
	private UriInfo uriInfo;
	
	public ExUriInfo(ResourceState serviceDocument, UriInfo uriInfo) {
		this.serviceDocument = serviceDocument;
		this.uriInfo = uriInfo;
	}
	
	@Override
	public String getPath() {
		return uriInfo.getPath();
	}

	@Override
	public String getPath(boolean decode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PathSegment> getPathSegments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PathSegment> getPathSegments(boolean decode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getRequestUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UriBuilder getRequestUriBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getAbsolutePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UriBuilder getAbsolutePathBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getBaseUri() {
		try {
			return new URI(AtomXMLProvider.getBaseUri(serviceDocument, uriInfo));
		} catch (URISyntaxException e) {
		    LOGGER.debug("Failed to get base uri", e);
		}
		return null;
	}

	@Override
	public UriBuilder getBaseUriBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultivaluedMap<String, String> getPathParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultivaluedMap<String, String> getPathParameters(boolean decode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultivaluedMap<String, String> getQueryParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultivaluedMap<String, String> getQueryParameters(boolean decode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMatchedURIs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMatchedURIs(boolean decode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getMatchedResources() {
		// TODO Auto-generated method stub
		return null;
	}

}
