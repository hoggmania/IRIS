package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.commons.io.IOUtils;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.PayloadHandler;
import com.temenos.useragent.generic.internal.DefaultEntityWrapper;
import com.temenos.useragent.generic.internal.EntityWrapper;
import com.temenos.useragent.generic.internal.LinkImpl;
import com.temenos.useragent.generic.internal.NullEntityWrapper;

/**
 * A payload handler for <i>application/atom+xml</i> media type.
 * 
 * @author ssethupathi
 *
 */
public class AtomPayloadHandler implements PayloadHandler {

	private AtomEntryHandler entityTransformer = new AtomEntryHandler();
	private boolean isCollection;
	private Feed feed;
	private String parameter; // not used yet

	public AtomPayloadHandler() {
		feed = new Abdera().newFeed();
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}

	public List<Link> links() {
		if (isCollection()) {
			return buildLinks(feed.getLinks());
		} else {
			return entityTransformer.getLinks();
		}
	}

	@Override
	public List<EntityWrapper> entities() {
		List<EntityWrapper> entityWrappers = new ArrayList<EntityWrapper>();
		for (Entry entry : feed.getEntries()) {
			EntityWrapper entityWrapper = new DefaultEntityWrapper();
			AtomEntryHandler entryHandler = new AtomEntryHandler();
			entryHandler.setEntry(entry);
			entityWrapper.setHandler(entryHandler);
			entityWrappers.add(entityWrapper);
		}
		return entityWrappers;
	}

	@Override
	public void setPayload(String payload) {
		if (payload == null) {
			throw new IllegalArgumentException("Payload is null");
		}
		Document<Element> payloadDoc = null;
		try {
			payloadDoc = new Abdera().getParser().parse(
					IOUtils.toInputStream(payload));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Unexpected payload for media type '" + AtomUtil.MEDIA_TYPE
							+ "'.", e);
		}
		QName rootElementQName = payloadDoc.getRoot().getQName();
		if (new QName(AtomUtil.NS_ATOM, "feed").equals(rootElementQName)) {
			feed = (Feed) payloadDoc.getRoot();
			isCollection = true;
		} else if (new QName(AtomUtil.NS_ATOM, "entry")
				.equals(rootElementQName)) {
			entityTransformer.setEntry((Entry) payloadDoc.getRoot());
			isCollection = false;
		} else {
			throw new IllegalArgumentException(
					"Unexpected payload for media type '" + AtomUtil.MEDIA_TYPE
							+ "'. Payload [" + payloadDoc.getRoot().toString()
							+ "]");
		}
	}

	@Override
	public EntityWrapper entity() {
		if (!isCollection) {
			EntityWrapper wrapper = new DefaultEntityWrapper();
			wrapper.setHandler(entityTransformer);
			return wrapper;
		}
		return new NullEntityWrapper();
	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	private List<Link> buildLinks(List<org.apache.abdera.model.Link> abderaLinks) {
		List<Link> links = new ArrayList<Link>();
		for (org.apache.abdera.model.Link abderaLink : abderaLinks) {
			links.add(new LinkImpl.Builder(abderaLink.getAttributeValue("href"))
					.baseUrl(AtomUtil.getBaseUrl(feed))
					.rel(AtomUtil.extractRel(abderaLink
							.getAttributeValue("rel")))
					.title(abderaLink.getAttributeValue("title"))
					.description(
							AtomUtil.extractDescription(abderaLink
									.getAttributeValue("rel"))).build());
		}
		return links;
	}
}
