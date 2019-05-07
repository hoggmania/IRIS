package com.temenos.interaction.core.entity.vocabulary.terms;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.entity.vocabulary.Term;

/**
 * This Term describes if metadata property should be displayed 
 * on client side or not.  
 */

public class TermRestriction extends AbstractOdataAnnotation implements Term {
	
	public final static String TERM_NAME = "TERM_RESTRICTION";
	
	// Default is no restriction
	private Restriction restriction = Restriction.NORESTRICTION;
	
	public TermRestriction(String restriction) {
		this.restriction = Restriction.getRestrictionType(restriction);
	}
	
	@Override
	public String getValue() {
		return restriction.getValue();
	}
	
	@Override
	public String getName() {
		return TERM_NAME;
	}
	
	/**
	 * This inner class would be used to define the type of restriction is applied on a property
	 * @author sjunejo
	 *
	 */
	public enum Restriction {
		DISPLAYONLY ("displayOnly"),
		FILTEREONLY ("filterOnly"),
		// just so that we do not have null
		NORESTRICTION ("NoRestriction");
		
		private String restrictionValue;
		
		private Restriction(String restrictionValue) {
			this.restrictionValue = restrictionValue;
		}
		
		public String getValue() {
			return restrictionValue;
		}
		
		// Retrieve the type of the Restriction we have
		public static Restriction getRestrictionType(String restriction) {
			if (restriction != null && !restriction.isEmpty()) {
				if (restriction.equalsIgnoreCase(Restriction.DISPLAYONLY.getValue())) 
					return DISPLAYONLY;
				else if (restriction.equalsIgnoreCase(Restriction.FILTEREONLY.getValue())) 
					return FILTEREONLY;
			}
			return NORESTRICTION;	
		}
	};
}
