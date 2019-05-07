package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import static com.temenos.interaction.core.loader.ResourceStateLoadingStrategy.ResourceStateResult;
import static org.junit.Assert.*;

/**
 * @author kwieconkowski
 */
public class TestSpringResourceStateLoadingStrategy {
    private static final String SPRING_PRD_FILE = "IRIS-testResources-PRD.xml";
    private static final String SPRING_EMPTY_PRD_FILE = "IRIS-empty-PRD.xml";
    private ResourceStateLoadingStrategy<String> loadingStrategy = new SpringResourceStateLoadingStrategy();

    @Test
    public void load_shouldReturnFilledList() {
        List<ResourceStateResult> result = loadingStrategy.load(SPRING_PRD_FILE);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void load_emptyFile_shouldReturnEmptyList() {
        List<ResourceStateResult> result = loadingStrategy.load(SPRING_EMPTY_PRD_FILE);
        assertTrue(result.isEmpty());
    }

    @Test
    public void load_fileNotExist_shouldRetrunNull() {
        assertNull(loadingStrategy.load("123.xml"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void load_nullLocation_shouldThrowException() {
        try {
            List<ResourceStateResult> result = loadingStrategy.load(null);
        } catch (IllegalArgumentException e) {
            assertEquals(getPrivateErrorMsgFromClass(loadingStrategy, "MSG_NAME_BLANK_OR_NULL"), e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void load_locationWithPath_shouldThrowException() {
        try {
            List<ResourceStateResult> result = loadingStrategy.load(File.separator + SPRING_PRD_FILE);
        } catch (IllegalArgumentException e) {
            assertEquals(getPrivateErrorMsgFromClass(loadingStrategy, "MSG_PATH_IN_NAME"), e.getMessage());
            throw e;
        }
    }

    private String getPrivateErrorMsgFromClass(Object clazz, String fieldName) {
        try {
            Field field = clazz.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(clazz);
        } catch (Exception e) {
            return "";
        }
    }
}