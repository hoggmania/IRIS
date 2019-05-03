package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.*;

import org.junit.Test;

public class ContentPrettyFormatterTest {

	@Test
	public void testFormatForValidXml() throws Exception {
		String originalContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><foo><bar>some text</bar></foo>";
		String formattedContent = ContentPrettyFormatter.newFormatter(
				"application/xml").format(originalContent);
		assertFalse(formattedContent.equals(originalContent));
		assertTrue(formattedContent.contains("<bar>some text</bar>"));
	}

	@Test
	public void testFormatForInvalidXml() {
		assertEquals(
				"<html><HR1><HR2></html>",
				ContentPrettyFormatter.newFormatter("application/xml").format(
						"<html><HR1><HR2></html>"));
	}

	@Test
	public void testFormatForValidJson() {
		String originalContent = "{\"foo\" : [{\"v1\" : \"bar1\"}, {\"v2\" : \"bar2\"}]}";
		String formattedContent = ContentPrettyFormatter.newFormatter(
				"application/hal+json").format(originalContent);
		assertFalse(formattedContent.equals(originalContent));
		assertTrue(formattedContent.contains("{\"v1\": \"bar1\"}"));
	}

	@Test
	public void testFormatForInvalidJson() {
		String invalidJson = "{\"foo\" : [{\"v1\" : \"bar1\"}, {\"v2\" : \"bar2\"]}";
		assertEquals(invalidJson,
				ContentPrettyFormatter.newFormatter("application/hal+json")
						.format(invalidJson));
	}

	@Test
	public void testFormatForUnknownType() {
		String someText = "$some [text] {expected} <unchanged>";
		assertEquals(someText, ContentPrettyFormatter.newFormatter(null)
				.format(someText));
		assertEquals(someText,
				ContentPrettyFormatter.newFormatter("").format(someText));
		assertEquals(
				someText,
				ContentPrettyFormatter.newFormatter("foo/bar+xml").format(
						someText));
	}
}
