package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.File;

/**
 * Interface for relating changes in files and directories with a collection of listeners.
 *
 * @author andres
 * @author trojan
 */
public interface DirectoryChangeDetector<LISTENER> extends ResourceChangeDetector<File, LISTENER> {

}
