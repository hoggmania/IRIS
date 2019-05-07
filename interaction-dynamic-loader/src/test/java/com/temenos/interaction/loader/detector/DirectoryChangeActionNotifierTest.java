package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.loader.detector.stub.DirectoryEventInterestedActionTestStub;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-contexts/directory-loader-test-context.xml" })
public class DirectoryChangeActionNotifierTest {

    @Autowired
    DirectoryEventInterestedActionTestStub action;
    
    @Autowired
    DirectoryChangeActionNotifier notifier;
    
    @Test
    public void test() throws IOException, InterruptedException {
       Assert.assertNotNull(action);
       Assert.assertNotNull(notifier);
       Assert.assertTrue(!notifier.getListeners().isEmpty());
       
       File tempDir = createTempDirectory();
       notifier.setResources(Collections.singletonList(tempDir));
       
       Thread.currentThread().sleep(3000);
       
       File tempFile = new File(tempDir,"test.file");
       tempFile.createNewFile();
       Thread.currentThread().sleep(3000);
       Assert.assertTrue(action.getCallCount() > 0);
       System.out.println(action.getLastEvent().getResource());
       FileUtils.forceDelete(tempDir);
    }
    
    public static File createTempDirectory()
    throws IOException
    {
        final File temp;
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        temp = new File(tempDir, "test"+System.nanoTime());

        if(!(temp.mkdirs()))
        {
            throw new IOException("Could not create temporary test dir");
        }

        return (temp);
    }

}
