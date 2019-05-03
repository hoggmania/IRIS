package com.temenos.interaction.media.odata.xml.atom;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.hypermedia.Link;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.resource.RESTResource;

public interface LinkInterceptor {

	/**
	 * <P>Called by the Provider when adding links to an {@link EntityResource}
	 * or {@link CollectionResource}.  The return Link will be added to the 
	 * representation, returning null will cause the link to be ignored.</P>
	 * @param resource the resource we are adding the link to
	 * @param linkToAdd
	 * @return
	 */
	public Link addingLink(RESTResource resource, Link linkToAdd);
	
}
