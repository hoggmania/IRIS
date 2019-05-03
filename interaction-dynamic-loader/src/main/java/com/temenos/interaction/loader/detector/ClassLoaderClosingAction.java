/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.loader.Action;

/**
 * Closes a ClassLoader marked to be closed and releases any system resources associated with it.
 * 
 * The ClassLoader needs to be an instance of Closeable.
 * 
 * @author andres
 * @author trojan
 */
public class ClassLoaderClosingAction implements Action<ClassLoader> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderClosingAction.class);

    ClassLoader previous = null;

    @Override
    public void execute(ClassLoader toClose) {
        try {
            if (previous != null) {
                ((Closeable) previous).close();
            }
        } catch (IOException ex) {
            LOGGER.error("Error closing the class loader.", ex);
        }
        if (!(toClose instanceof Closeable)) {
            previous = null;
        } else {
            previous = toClose;
        }
    }

}
