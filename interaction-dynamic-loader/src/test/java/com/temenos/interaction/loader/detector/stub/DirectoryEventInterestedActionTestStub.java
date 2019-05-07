package com.temenos.interaction.loader.detector.stub;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.FileEvent;
import java.io.File;

/**
 *
 * @author andres
 */
public class DirectoryEventInterestedActionTestStub implements Action<FileEvent<File>> {
    
    private int callCount = 0;
    private FileEvent<File> lastEvent;
    
    @Override
    public void execute(FileEvent<File> event) {
       callCount++;
       lastEvent = event;
    }
    
    public int getCallCount() {
        return callCount;
    }

    /**
     * @return the lastEvent
     */
    public FileEvent<File> getLastEvent() {
        return lastEvent;
    }
    
}
