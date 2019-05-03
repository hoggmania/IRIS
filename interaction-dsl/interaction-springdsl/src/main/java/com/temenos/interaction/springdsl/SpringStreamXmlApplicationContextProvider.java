package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

/**
 * This class  creates and loads the Bean definition from the Inputstream.
 *
 * @author mohamednazir
 *
 */
public class SpringStreamXmlApplicationContextProvider extends AbstractXmlApplicationContext {

    private Resource[] configResources = null;

    public SpringStreamXmlApplicationContextProvider(InputStream configFileStream) {
        super();
        this.configResources = new Resource[] { new InputStreamResource(configFileStream) };
        this.refresh();
    }

    @Override
    protected Resource[] getConfigResources() {
        return this.configResources;
    }

    @Override
    public void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException, IOException {
        reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
        super.loadBeanDefinitions(reader);
    }
}
