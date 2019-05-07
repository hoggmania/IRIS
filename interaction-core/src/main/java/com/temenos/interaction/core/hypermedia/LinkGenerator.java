package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.MultivaluedMap;
import java.util.Collection;


/**
 * A LinkGenerator is used to generate a {@link Collection} of {@link Link}
 * for a {@link Transition} using data from a resource entity.
 *
 */
public interface LinkGenerator {

    /**
     * Create a {@link Collection} of {@link Link} using supplied path parameters,
     * query parameters and entity.
     *
     * @param pathParameters path parameters sent in request
     *
     * @param queryParameters query parameters sent in request
     *
     * @param entity resource entity coming back in response
     *
     * @return {@link Collection} of {@link Link}
     */
    public Collection<Link> createLink(MultivaluedMap<String, String> pathParameters,
            MultivaluedMap<String, String> queryParameters, Object entity);

}
