package com.temenos.interaction.rimdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import com.temenos.interaction.rimdsl.rim.DomainDeclaration;
import com.temenos.interaction.rimdsl.rim.DomainModel;

@InjectWith(RIMDslSwaggerInjectorProvider.class)
@RunWith(XtextRunner.class)
public class SwaggerGeneratorTest {
	
	private final static Logger LOGGER = Logger.getLogger(SwaggerGeneratorTest.class.getName());
	
	@Inject 
	IGenerator underTest;
	@Inject
	ParseHelper<DomainModel> parseHelper;

	@Test
	public void testGenerateSimpleStates() throws Exception {
		DomainModel domainModel = parseHelper.parse(loadTestResource("apiSimple.rim"));
		DomainDeclaration domainDeclaration = (DomainDeclaration) domainModel.getRims().get(0);
		InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
		underTest.doGenerate(domainDeclaration.eResource(), fsa);		
		assertEquals(1, fsa.getFiles().size());
		
		// the behaviour class
		String expectedKey = IFileSystemAccess.DEFAULT_OUTPUT + "api-docs-SimpleDomain-Simple.json";
		assertTrue(fsa.getFiles().containsKey(expectedKey));
		assertEquals(loadTestResource("api-docs-SimpleDomain-Simple.json"), fsa.getFiles().get(expectedKey));
	}

	@Test
	public void testGenerateSimple() throws Exception {
		DomainModel domainModel = parseHelper.parse(loadTestRIM());
		DomainDeclaration domainDeclaration = (DomainDeclaration) domainModel.getRims().get(0);
		InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
		underTest.doGenerate(domainDeclaration.eResource(), fsa);
		assertEquals(1, fsa.getFiles().size());
		
		// the behaviour class
		String expectedKey = IFileSystemAccess.DEFAULT_OUTPUT + "api-docs-SimpleModel-Simple.json";
		assertTrue(fsa.getFiles().containsKey(expectedKey));
		String output = fsa.getFiles().get(expectedKey).toString();
		assertTrue(output.contains("/notes"));
	}
	
	private String loadTestRIM() throws IOException {
		URL url = Resources.getResource("Simple.rim");
		String rim = Resources.toString(url, Charsets.UTF_8);
		return rim;
	}
	
	private String loadTestResource(String resource) throws IOException {
        URL url = Resources.getResource(resource);
        String file = Resources.toString(url, Charsets.UTF_8);
        return file;
    }
}
