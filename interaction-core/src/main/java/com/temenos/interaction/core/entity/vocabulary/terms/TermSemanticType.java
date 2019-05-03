package com.temenos.interaction.core.entity.vocabulary.terms;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.entity.vocabulary.Term;

/**
 * This Term describes the semantic type of a property, 
 * e.g. currency code, person name, money amount, etc. 
 */
public class TermSemanticType extends AbstractOdataAnnotation implements Term {
	
	public final static String TERM_NAME = "TERM_SEMANTIC_TYPE";
	public final static String CSDL_NAME = "semanticType";
	
	private String value;
	
	public TermSemanticType(String val) {
		this.value = val;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String getName() {
		return TERM_NAME;
	}	
}
