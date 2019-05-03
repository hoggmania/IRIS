package com.temenos.interaction.rimdsl.generator.launcher;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.temenos.interaction.core.entity.EntityMetadata;
import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.rimdsl.rim.State;

/**
 * This class generates code from a DSL model.
 * @author aphethean
 *
 */
public class Generator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class);
	
	@Inject
    private XtextResourceSet resourceSet;
	@Inject
	private IResourceValidator validator;
	@Inject
	private IGenerator generator;
	@Inject
	private JavaIoFileSystemAccess fileAccess;
	
	private ValidatorEventListener listener = new ValidatorEventListener() {		
		public void notify(String msg) {
			System.err.println(msg);
		}
	};

	public boolean runGeneratorDir(String inputDirPath, String outputPath) {
		List<String> files = getFiles(inputDirPath, ".rim");
		for (String modelPath : files) {
			resourceSet.getResources().add(resourceSet.getResource(URI.createFileURI(modelPath), true));
		}
		boolean result = true;
		for (String modelPath : files) {
			boolean fileResult = runGenerator(modelPath, outputPath);
			if (!fileResult) {
				result = fileResult;
			}
		}
		return result;
	}
	
	public boolean runGeneratorDir(String inputDirPath, Metadata metadata, String outputPath) {
        List<String> files = getFiles(inputDirPath, ".rim");
        for (String modelPath : files) {
            resourceSet.getResources().add(resourceSet.getResource(URI.createFileURI(modelPath), true));
        }
        boolean result = true;
        for (String modelPath : files) {
            boolean fileResult = runGenerator(modelPath, metadata, outputPath);
            if (!fileResult) {
                result = fileResult;
            }
        }
        return result;
    }
	
	protected String toSystemFileName(String fileName) {
		return fileName.replace("/", File.separator);
	}

	/**
	 * @param path a folder path
	 * @param extension a file extension
	 * @return a list of files contained in the specified folder and
	 * 		its sub folders filtered by extension
	 */
	protected ArrayList<String> getFiles(String path, String extension) {
		ArrayList<String> result = new ArrayList<String>();
		
		path = toSystemFileName(path);
		getFilesRecursively(path, result, extension);
		
		return result;
	}

	private void getFilesRecursively(String path, ArrayList<String> result, String extension) {
		File file = new File(path);
		if (file.isDirectory()) {
			String[] contents = file.list();
			for (String sub : contents) {
				getFilesRecursively(path + File.separator + sub, result, extension);
			}
		} else {
			if (file.getName().endsWith(extension))
				result.add(file.getAbsolutePath());
		}
	}
	
	public boolean runGenerator(String inputPath, String outputPath) {
	    return runGenerator(inputPath, null, outputPath);
	}
	
	public boolean runGenerator(String inputPath, Metadata metadata, String outputPath) {
		
		// load the resource
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(URI.createFileURI(inputPath), true);
        
		if(metadata!=null) {		    
		    Map<String, Object> entitiesMap = new HashMap<String, Object>();	
		    for(State key : Iterables.<State>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), State.class)) {		        
                String entity = key.getEntity().getName();                
                if (StringUtils.isNotEmpty(entity)) {                
                    try {                        
                        EntityMetadata em = metadata.getEntityMetadata(entity);                        
                        if (null != em) {                            
                            Map<String, Object> entityPropMap = new HashMap<String, Object>();
                            for (String propertySimple : em.getTopLevelProperties()) {                           
                                if(!em.isPropertyList(propertySimple)) {
                                    
                                    ArrayList<String> obj = new ArrayList<String>();
                                    
                                    String propertyName = em.getSimplePropertyName(propertySimple);
                                                                       
                                    if (em.isPropertyNumber(propertySimple)) {                                        
                                        obj.add(0, "double");
                                    } else if (em.isPropertyDate(propertySimple)) {                                        
                                        obj.add(0, "date");
                                    } else if (em.isPropertyTime(propertySimple)) {                        
                                        obj.add(0, "dateTime");
                                    } else if (em.isPropertyBoolean(propertySimple)) {      
                                        obj.add(0, "boolean");
                                    } else {
                                        obj.add(0, "string");
                                    }
                                   
                                    String description = em.getTermValue(propertySimple, "TERM_DESCRIPTION");
                                    description = (null != description) ? description : "";
                                    obj.add(1, description);
                                    
                                    entityPropMap.put(propertyName, obj);
                                    
                                } else {                                    
                                    String propertyName = em.getSimplePropertyName(propertySimple);
                                    entityPropMap.put(propertyName, complexTypeHandler(propertySimple, em));
                                }
                            }                            
                            entitiesMap.put(entity, entityPropMap);
                        }                        
                    } catch (Exception e) {
                        LOGGER.error("Entity Not found: " + entity, e);
                    }
                }
		    }
		    resource.getResourceSet().getLoadOptions().put("Metadata", entitiesMap);
		}

		// validate the resource
		List<Issue> list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		if (!list.isEmpty()) {
			for (Issue issue : list) {								
				listener.notify(issue.toString());
			}
			
			return false;
		}

		// configure and start the generator
		fileAccess.setOutputPath(outputPath);
		generator.doGenerate(resource, fileAccess);
		
		return true;
	}
	
	public void setValidatorEventListener(ValidatorEventListener listener) {
		if(listener != null) {
			this.listener = listener;
		}
	}	
	
	public Map<String, Object> complexTypeHandler(String propertyName, EntityMetadata em) {
	    
	    Map<String, Object> map = new HashMap<>();
	    
	    // 1st level
	    
	    for (String property : em.getPropertyVocabularyKeySet()) {
	        
	        if(property.startsWith(propertyName) && !property.equals(propertyName)) {
	            
	            if(em.isPropertyList(property)) {
	                // Complex
	                    
	                Map<String,Object> subMap = complexTypeHandler(property, em);
	                map.put(em.getSimplePropertyName(property),subMap);
	            
	            } else {
	                // SIMPLE	                
	                Pattern pattern = Pattern.compile("(?:"+propertyName+")(?:\\.)(.*)");
	                Matcher matcher = pattern.matcher(property);
	                while(matcher.find()) {
	                    String propertyCapture = matcher.group(1);
	                    if(em.getSimplePropertyName(property).equals(propertyCapture)) {
                            ArrayList<String> obj = new ArrayList<String>();
	                        if (em.isPropertyNumber(property)) {                  
	                            obj.add(0, "double");
                            } else if (em.isPropertyDate(property)) { 
                                obj.add(0, "date");
                            } else if (em.isPropertyBoolean(property)) { 
                                obj.add(0, "boolean");
                            } else {
                                obj.add(0, "string");
                            }

	                        String description = em.getTermValue(property, "TERM_DESCRIPTION");
                            description = (null != description) ? description : "";
                            obj.add(1, description);
                            
	                        map.put(em.getSimplePropertyName(property), obj);
	                    }
	                }
	            }
	        }
	    }
	    
	    return map;	    
	}
}