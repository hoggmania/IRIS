package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static com.temenos.useragent.generic.mediatype.AtomUtil.MEDIA_TYPE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.ParseException;
import org.apache.commons.io.IOUtils;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.internal.EntityHandler;
import com.temenos.useragent.generic.internal.LinkImpl;
import com.temenos.useragent.generic.internal.Payload;

/**
 * An {@link EntityHandler entity handler} implementation for Atom Entry type in
 * <i>application/atom+xml</i> media type.
 * <p>
 * 
 * @see <a
 *      href="https://tools.ietf.org/html/rfc4287#section-4.1.2">atom:entry</a>
 *      element.
 *      </p>
 * @author ssethupathi
 *
 */
public class AtomEntryHandler implements EntityHandler {

    private Entry entry;
    private AtomXmlContentHandler xmlContentHandler;
    private boolean entryNotSet = true;

    public List<Link> getLinks() {
        validateHandler();
        return convertLinks(entry.getLinks());
    }

    public String getId() {
        validateHandler();
        String fullPath = entry.getIdElement().getText();
        if (fullPath.contains("('") && fullPath.endsWith("')")) {
            return fullPath.substring(fullPath.indexOf("'") + 1, fullPath.lastIndexOf("'"));
        }
        return "";
    }

    public int getCount(String fqPropertyName) {
        validateHandler();
        return xmlContentHandler.getCount(fqPropertyName);
    }

    public String getValue(String fqPropertyName) {
        validateHandler();
        return xmlContentHandler.getValue(fqPropertyName);
    }

    @Override
    public <T> void setValue(String fqPropertyName, T value) {
        validateHandler();
        xmlContentHandler.setValue(fqPropertyName, value != null ? String.valueOf(value) : null);
    }
    
    @Override
    public void remove(String fqPropertyName) {
    	validateHandler();
    	xmlContentHandler.remove(fqPropertyName);
    }
    
	@Override
	public Payload embedded() {
		// atom xml supports embedded entities only in links
		return null;
	}

    private List<Link> convertLinks(List<org.apache.abdera.model.Link> abderaLinks) {
        List<Link> links = new ArrayList<Link>();
        for (org.apache.abdera.model.Link abderaLink : abderaLinks) {
            AtomLinkHandler linkHandler = new AtomLinkHandler(abderaLink);
            links.add(new LinkImpl.Builder(abderaLink.getAttributeValue("href")).baseUrl(linkHandler.getBaseUri())
                    .rel(linkHandler.getRel()).id(getId()).title(abderaLink.getAttributeValue("title"))
                    .description(AtomUtil.extractDescription(abderaLink.getAttributeValue("rel")))
                    .payload(linkHandler.getEmbeddedPayload()).build());
        }
        return links;
    }

    @Override
    public void setContent(InputStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Entity input stream is null");
        }
        Document<Element> entityDoc = null;
        try {
            entityDoc = new Abdera().getParser().parse(stream);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Unexpected entity for media type '" + AtomUtil.MEDIA_TYPE + "'.", e);
        }
        QName rootElementQName = entityDoc.getRoot().getQName();
        if (new QName(AtomUtil.NS_ATOM, "entry").equals(rootElementQName)) {
            initHandler((Entry) entityDoc.getRoot());
        } else {
            throw new IllegalArgumentException("Unexpected entity for media type '" + MEDIA_TYPE + "'. Payload ["
                    + entityDoc.getRoot().toString() + "]");
        }
    }

    public void setEntry(Entry entry) {
        initHandler(entry);
    }

	private void initHandler(Entry entry) {
		this.entry = entry;
		org.w3c.dom.Document contentDocument = AtomUtil.buildXmlDocument(entry
				.getContent());
		if (contentDocument != null) { // entry with no content and only links
			this.xmlContentHandler = new AtomXmlContentHandler(contentDocument);
		}
		entryNotSet = false;
	}

	private void validateHandler() {
		if (entryNotSet || xmlContentHandler == null) {
			throw new IllegalStateException("Entity handler '"
					+ this.getClass().getName()
					+ "' is not set with any content");
		}
	}

    @Override
    public InputStream getContent() {
        validateHandler();
        AtomUtil.updateEntryContent(xmlContentHandler.getDocument(), entry);
        return IOUtils.toInputStream(getContent(entry));
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