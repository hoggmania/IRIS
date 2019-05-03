package com.temenos.interaction.sdk.plugin;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.*;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

public class TestRIMGeneratorMojo {

	@Test (expected = MojoExecutionException.class)
	public void testNullRimSourceFile() throws MojoExecutionException, MojoFailureException {
		RIMGeneratorMojo mojo = new RIMGeneratorMojo();
		mojo.setRimSourceFile(null);
		mojo.execute();
	}

	@Test (expected = MojoExecutionException.class)
	public void testSkipRIMGeneration() throws MojoExecutionException, MojoFailureException {
		RIMGeneratorMojo mojo = new RIMGeneratorMojo();
		mojo.setSkipRIMGeneration("true");
		mojo.setSkipSwaggerGeneration("true");
		// will throw error if generation is not skipping properly
		mojo.execute();
	}

	@Test (expected = MojoExecutionException.class)
	public void testRIMGeneration() throws MojoExecutionException, MojoFailureException {
		RIMGeneratorMojo mojo = new RIMGeneratorMojo();
		mojo.setSkipRIMGeneration("false");
		mojo.setSkipSwaggerGeneration("true");
		// will throw error if generation is not skipping properly
		mojo.execute();
	}

	/**
	 * Test rim generation spring prd.
	 *
	 * @throws MojoExecutionException the mojo execution exception
	 * @throws MojoFailureException the mojo failure exception
	 */
	@Test 
	public void testRIMGenerationSpringPRD() throws MojoExecutionException, MojoFailureException {
		RIMGeneratorMojo mojo = new RIMGeneratorMojo();
		
	    File rimSourceFile = new File("src/test/rim/Simple.rim");
	    File targetDirectory = new File("target/springdsl");
		mojo.setSkipRIMGenerationSpringPRD("false");
		mojo.setSkipSwaggerGeneration("true");
		mojo.setSkipRIMGeneration("true");
		mojo.setTargetDirectory(targetDirectory);
		mojo.setRimSourceFile(rimSourceFile);

		// will throw error if generation is not skipping properly
		mojo.execute();
		
		assertEquals(2, targetDirectory.list().length);
	}

}
