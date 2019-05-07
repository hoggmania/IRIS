package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.temenos.interaction.core.resource.ConfigLoader;

/**
 * This class enhances the Spring PropertiesFactoryBean class to provide support for loading IRIS configuration files 
 * from a directory defined as a system property.
 */
public class SpringDSLPropertiesFactoryBean extends PropertiesFactoryBean {
	
	private String filenamePattern;
	private ConfigLoader configLoader = new ConfigLoader();

	/**
	 * @param filenamePattern The file name pattern to use if properties files are being loaded from the file system
	 */
	public SpringDSLPropertiesFactoryBean(String filenamePattern) {
		this.filenamePattern = filenamePattern;
	}
		
	/**
	 * Sets the alternative config loader to use
	 *  
	 * @param configLoader The alternative config loader to use
	 */
	@Autowired(required = false)	
	public void setConfigLoader(ConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	@Override
	public void setLocations(Resource[] locations) {
		List<Resource> tmpLocations = new ArrayList<Resource>();
		Map<String, Boolean> fileNames = new HashMap<String, Boolean>();
		tmpLocations.addAll(Arrays.asList(locations));

		for(String pathToDirectory : configLoader.getIrisConfigDirPaths()) {
			File irisResourceDir = new File(pathToDirectory);
			File[] files = irisResourceDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.matches(filenamePattern);
				}
			});
			for(File file: files) {
				// Create a resource for a current file and add it to the collection of properties resources
				if(!fileNames.containsKey(file.getName())) {
					fileNames.put(file.getName(),true);
					tmpLocations.add(new FileSystemResource(file));
				}
			}
		}

		super.setLocations(tmpLocations.toArray(new Resource[0]));
	}
}