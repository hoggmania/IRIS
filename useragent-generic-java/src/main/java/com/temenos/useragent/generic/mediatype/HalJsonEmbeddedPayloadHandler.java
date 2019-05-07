package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static com.temenos.useragent.generic.mediatype.HalJsonUtil.extractEmbeddedLinks;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.PayloadHandler;
import com.temenos.useragent.generic.internal.EntityWrapper;
import com.temenos.useragent.generic.internal.NullEntityWrapper;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;


/**
 * hal+json embedded content payload handler
 *
 * @author sathisharulmani
 *
 */
public class HalJsonEmbeddedPayloadHandler implements PayloadHandler {

    private RepresentationFactory representationFactory = HalJsonUtil.initRepresentationFactory();
    private ReadableRepresentation representation = representationFactory.newRepresentation();

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public List<Link> links() {
        return extractEmbeddedLinks(representation);
    }

    @Override
    public List<EntityWrapper> entities() {
        return new ArrayList<EntityWrapper>();
    }

    @Override
    public EntityWrapper entity() {
        return new NullEntityWrapper();
    }

    @Override
    public void setPayload(String payload) {
        if (payload == null) {
            return;
        }
        ReadableRepresentation jsonRepresentation = representationFactory.readRepresentation(
                RepresentationFactory.HAL_JSON, new InputStreamReader(IOUtils.toInputStream(payload)));
        representation = jsonRepresentation;
    }

    @Override
    public void setParameter(String parameter) {
    }

}
