package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.FileEvent;
import com.temenos.interaction.loader.classloader.ParentLastURLClassloader;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;

/**
 * Loads classes from files with specified extensions in a given directory.
 * It also gives the option to set listeners and execute actions on the loaded classes.
 * 
 * @author andres
 * @author trojan
 */
public class ClassLoaderCreatingAction implements Action<FileEvent<File>> {

    private Collection<? extends Action<ClassLoader>> listeners = new ArrayList();
    private List<String> extensions = Collections.singletonList("jar");

    /**
     * @param fe the file event with the directory to search for the specified file extensions
     */
    @Override
    public void execute(FileEvent<File> fe) {
       Collection<File> files = FileUtils.listFiles(fe.getResource(), extensions.toArray(new String[]{}), true);
       Set<URL> urls = new HashSet();
       for (File f : files) {
           try {
               urls.add(f.toURI().toURL());
           } catch (MalformedURLException ex) {
              // TODO: log properly
           }
       }
       ParentLastURLClassloader classloader = new ParentLastURLClassloader(urls.toArray(new URL[]{}));

       for (Action<ClassLoader> listener : listeners) {
           listener.execute(classloader);
       }
    }
  
    public Collection<? extends Action<ClassLoader>> getListeners() {
        return listeners;
    }

    public void setListeners(Collection<? extends Action<ClassLoader>> listeners) {
        this.listeners = new ArrayList(listeners);
    }

    public List<String> getExtensions() {
        return extensions;
    }
    
    /**
     * @param extensions list of file extensions to look for
     */
    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

}
