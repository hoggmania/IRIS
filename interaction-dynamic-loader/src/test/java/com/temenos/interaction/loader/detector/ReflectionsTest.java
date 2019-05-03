package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.command.annotation.InteractionCommandImpl;
import com.temenos.interaction.loader.classloader.ParentLastURLClassloader;
import com.temenos.annotatedtestclasses.AnnotatedInteractionCmdStubImpl1;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;


/**
 * The tests verifies if the class already existing on the classpath can be reloaded from a JAR coocked up for the person.
 * It also test scanning for annotations in the prescribed group of JARs only.
 * @author andres
 * @author trojan
 */

public class ReflectionsTest {
    
    @Test
    public void testLoadingClassesFromJar() throws MalformedURLException, ClassNotFoundException {
        File jarFile = new File("src/test/jars/AnnotatedTestInteractionCommandClasses.jar");
        Assert.assertTrue(jarFile.exists());
        ClassLoader classloader = new ParentLastURLClassloader(new URL[]{jarFile.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
        Class<?> clz = classloader.loadClass("com.temenos.annotatedtestclasses.AnnotatedInteractionCmdStubImpl1");
        Assert.assertEquals("Annotation name was not read as expected", "testName1", clz.getAnnotation(InteractionCommandImpl.class).name());
    }
    
    @Test
    public void testReflectionsOnSpecificPackage() throws MalformedURLException {
        // enforce loading class with current classloader
        AnnotatedInteractionCmdStubImpl1 object = new AnnotatedInteractionCmdStubImpl1();
        
        File jarFile = new File("src/test/jars/AnnotatedTestInteractionCommandClasses.jar");
       
        ClassLoader classloader = new ParentLastURLClassloader(new URL[]{jarFile.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
        Reflections r = new Reflections(                
                new ConfigurationBuilder()
            .setUrls(jarFile.toURI().toURL())
            .addClassLoader(classloader)
        );
        
        Set<Class<?>> annotated = r.getTypesAnnotatedWith(InteractionCommandImpl.class);
        
        // we knew 3 classes with given annotation was in a jar we prepared
        Assert.assertEquals("The number of classes detected is different than expected",3, annotated.size());
        for (Class cls : annotated) {
            // for every class chack if it was really loaded with the classloader we wanted
            // AnnotatedClass1 - in case of classloading method being faulty would be from parent!
            Assert.assertEquals("Classloader used to load class different than expected, delegation model failed!", cls.getClassLoader(), classloader);
        }
    }
    
}
