package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static com.temenos.useragent.generic.mediatype.AtomUtil.NS_ATOM;
import static com.temenos.useragent.generic.mediatype.AtomUtil.NS_ODATA_METADATA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.xml.namespace.QName;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Link;

import com.temenos.useragent.generic.PayloadHandler;
import com.temenos.useragent.generic.context.ContextFactory;
import com.temenos.useragent.generic.http.DefaultHttpClientHelper;
import com.temenos.useragent.generic.internal.DefaultPayloadWrapper;
import com.temenos.useragent.generic.internal.Payload;
import com.temenos.useragent.generic.internal.PayloadHandlerFactory;
import com.temenos.useragent.generic.internal.PayloadWrapper;

/**
 * Handler for links in Atom models.
 * 
 * @author ssethupathi
 *
 */
public class AtomLinkHandler {

	private Link abderaLink;
	private Payload embeddedPayload;

	public AtomLinkHandler(Link abderaLink) {
		this.abderaLink = abderaLink;
	}

	public String getTitle() {
		return abderaLink.getTitle();
	}

	public String getHref() {
		return abderaLink.getAttributeValue("href");
	}

	public String getRel() {
		return AtomUtil.extractRel(abderaLink.getAttributeValue("rel"));
	}

	public String getDescription() {
		return AtomUtil.extractDescription(abderaLink.getAttributeValue("rel"));
	}

	public String getBaseUri() {
		try {
			return abderaLink.getBaseUri().toURL().toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Payload getEmbeddedPayload() {
		if (embeddedPayload == null) {
			buildEmbeddedPayload();
		}
		return embeddedPayload;
	}

	private void buildEmbeddedPayload() {
		Element inlineElement = abderaLink.getFirstChild(new QName(
				NS_ODATA_METADATA, "inline"));
		if (inlineElement == null || inlineElement.getFirstChild() == null) {
			embeddedPayload = null; // TODO null payload
			return;
		}
		Element feedElement = inlineElement.getFirstChild(new QName(NS_ATOM,
				"feed"));
		String content = "";
		if (feedElement != null) {
			content = getContent(feedElement);
		} else {
			Element entryElement = inlineElement.getFirstChild(new QName(
					NS_ATOM, "entry"));
			//As we lose the reference to the parent after the conversion to String, we need to explicitly set the baseUri
			entryElement.setBaseUri(abderaLink.getBaseUri());
			if (entryElement != null) {
				content = getContent(entryElement);
			}
		}
		
		String contentType = abderaLink.getAttributeValue("type")!=null?abderaLink.getAttributeValue("type"):AtomUtil.MEDIA_TYPE;
		PayloadHandlerFactory<? extends PayloadHandler> factory = ContextFactory
				.get()
				.getContext()
				.entityHandlersRegistry()
				.getPayloadHandlerFactory(
						DefaultHttpClientHelper.removeParameter(contentType));
		PayloadHandler handler = factory.createHandler(content);
		PayloadWrapper wrapper = new DefaultPayloadWrapper();
		wrapper.setHandler(handler);
		embeddedPayload = wrapper;
	}

	private String getContent(Element element) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			element.writeTo(baos);
			return baos.toString("UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
