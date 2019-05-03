package com.interaction.example.odata.airline;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.core.NamespacedAnnotation;
import org.odata4j.core.PrefixedNamespace;
import org.odata4j.edm.EdmDataServices;
import org.odata4j.edm.EdmSimpleType;
import org.odata4j.jersey.consumer.ODataJerseyConsumer;

/**
 * Test that semantic types represented as Annotation attributes
 * are passed over the OData interface 
 * @author Andrew McGuinness
 */
public class EdmAnnotationITCase {

	private static final String annotationNamespace = "http://iris.temenos.com/odata-extensions";
	@Test
	public void testSemanticAnnotation() {
		ODataConsumer consumer = ODataJerseyConsumer.newBuilder(ConfigurationHelper.getTestEndpointUri(Configuration.TEST_ENDPOINT_URI)).build();
		boolean oldDump = ODataConsumer.dump.responseBody();
		ODataConsumer.dump.responseBody(true);
		
		EdmDataServices metadata = consumer.getMetadata();
		
		boolean supportsAnnotations = false;
		List<PrefixedNamespace> namespaces = metadata.getNamespaces();
		for (PrefixedNamespace n : namespaces)
			if (n.getUri().equals(annotationNamespace))
				supportsAnnotations = true;

		Assert.assertEquals(EdmSimpleType.STRING,
				metadata.findEdmEntitySet("Airports").getType()
						.findProperty("country").getType());
		
		if(supportsAnnotations) {
			NamespacedAnnotation<?> st = metadata.findEdmEntitySet("Airports").getType().findProperty("country").findAnnotation( annotationNamespace, "semanticType");
			Assert.assertNotNull(st);
			Assert.assertEquals("Geography:Country", st.getValue());
		}		
		ODataConsumer.dump.responseBody(oldDump);
	}
	
}
