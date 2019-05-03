package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A formatter for human readable pretty printing of string content according to
 * a given media type.
 * 
 * @author ssethupathi
 *
 */
public class ContentPrettyFormatter {

	private DataType dataType;

	private ContentPrettyFormatter(DataType dataType) {
		this.dataType = dataType;
	}

	public String format(String content) {
		if (content == null || content.isEmpty()) {
			return content;
		}
		switch (dataType) {
		case XML:
			return prettyPrintXml(content);
		case JSON:
			return prettyPrintJson(content);
		default:
			return content;
		}
	}

	private String prettyPrintXml(String xmlContent) {
		try {
			Source xmlInput = new StreamSource(new StringReader(xmlContent));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			transformerFactory.setAttribute("indent-number", 4);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			// Not a valid XML document so return as-is
			return xmlContent;
		}
	}

	private String prettyPrintJson(String jsonContent) {
		try {
			JSONObject json = new JSONObject(jsonContent);
			return json.toString(4);
		} catch (JSONException e) {
			// Not a valid JSON document so return as-is
			return jsonContent;
		}
	}

	public static ContentPrettyFormatter newFormatter(String mediaType) {
		return new ContentPrettyFormatter(DataType.getType(mediaType));
	}

	private enum DataType {
		XML, JSON, UNKNOWN;

		private static List<String> xmlTypes = Arrays.asList(new String[] {
				"application/atom+xml", "application/xml",
				"application/xhtml+xml", "text/xml" });

		private static List<String> jsonTypes = Arrays.asList(new String[] {
				"application/hal+json", "application/json" });

		static DataType getType(String mediaType) {
			return xmlTypes.contains(mediaType) ? XML : jsonTypes
					.contains(mediaType) ? JSON : UNKNOWN;
		}
	}

}
