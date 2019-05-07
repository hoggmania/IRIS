package com.temenos.interaction.loader.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.FileEvent;

/**
 * TODO: Document me!
 *
 * @author mlambert
 *
 */
public class AbstractResourceModificationAction {
	private String resourcePatternStr;
	protected Pattern resourcePattern;	
	
	public final void setResourcePattern(String resourcePatternStr) {
		this.resourcePatternStr = resourcePatternStr;
		
		String tmp = resourcePatternStr;

		if(resourcePatternStr.indexOf(":") > -1) {
			// Ignore pattern prefixes like classpath*: as actual the file name in events will not contain them
			tmp = tmp.substring(resourcePatternStr.indexOf(":") + 1);
		}
		
		// Switch from Spring regex to Java regex
		tmp = tmp.replace("*", ".*");

		this.resourcePattern = Pattern.compile(tmp);
	}
	
	public String getResourcePattern() {
		return resourcePatternStr;
	}
	
	protected final boolean matches(FileEvent<Resource> event) {
		Resource resource = event.getResource(); 
		String filename = resource.getFilename();

		boolean result = false;
		
		if(filename != null) {
			Matcher matcher = resourcePattern.matcher(filename);

			if(matcher.find()) {
				result = true;
			}
		}
		
		return result;
	}				
}
