package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.temenos.interaction.core.hypermedia.ResourceState;

/**
 * Loads a list of ResourceState from a prd name, which should be a filename
 * without a path. This is for compatibility with the Spring class
 * ClassPathXmlApplicationContext.
 *
 * @author kwieconkowski
 * @author andres
 * @author dgroves
 */
public class SpringResourceStateLoadingStrategy implements ResourceStateLoadingStrategy<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringResourceStateLoadingStrategy.class);
    private static final String MSG_NAME_BLANK_OR_NULL = "Passed PRD file name is NULL or empty";
    private static final String MSG_PATH_IN_NAME = "Spring PRD file name must not contain the path";

    @Override
    public List<ResourceStateResult> load(String nameOfSpringFile) {
        validateSpringNameOtherwiseThrowException(nameOfSpringFile);
        ApplicationContext PrdAppCtx = loadSpringContext(nameOfSpringFile);
        
        if (PrdAppCtx == null) {
            LOGGER.warn("File not found while loading spring configuration in name: " + nameOfSpringFile);
            return null;
        }
        
        List<ResourceStateResult> resourceStates = new ArrayList<ResourceStateResult>();
        
        for (Map.Entry<String, ResourceState> springBean : PrdAppCtx.getBeansOfType(ResourceState.class).entrySet()) {
            resourceStates.add(new ResourceStateResult(springBean.getKey(), springBean.getValue()));
        }
        
        LOGGER.info("Resource states loaded from spring configuration xml: " + nameOfSpringFile);
        
        return resourceStates;
    }

    private ApplicationContext loadSpringContext(String nameOfSpringFile) {
        ApplicationContext PrdAppCtx = null;
        
        try {
            PrdAppCtx = new ClassPathXmlApplicationContext(nameOfSpringFile);
        } catch (Exception e) {
            LOGGER.error("Failed to create context from: " + nameOfSpringFile, e);
        }
        
        return PrdAppCtx;
    }

    private void validateSpringNameOtherwiseThrowException(String nameOfSpringFile) {
        if (nameOfSpringFile == null || nameOfSpringFile.isEmpty()) {
            LOGGER.error(MSG_NAME_BLANK_OR_NULL);
            
            throw new IllegalArgumentException(MSG_NAME_BLANK_OR_NULL);
        } else if (!Paths.get(nameOfSpringFile).getFileName().toString().equals(nameOfSpringFile)) {
            LOGGER.error(MSG_PATH_IN_NAME);
            
            throw new IllegalArgumentException(MSG_PATH_IN_NAME);
        }
    }
}