package com.temenos.interaction.core.rim;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AcceptLanguageHeaderParserTest {

	@Test
	public void testGetLanguageCodesForInvalidValues() {
		List<String> preferredLanguages = new AcceptLanguageHeaderParser(null).getLanguageCodes();
		assertEquals(0, preferredLanguages.size());

		preferredLanguages = new AcceptLanguageHeaderParser("").getLanguageCodes();
		assertEquals(0, preferredLanguages.size());

		preferredLanguages = new AcceptLanguageHeaderParser(",").getLanguageCodes();
		assertEquals(0, preferredLanguages.size());
		
		preferredLanguages = new AcceptLanguageHeaderParser("en-gb,en;foo").getLanguageCodes(); // invalid quality value part
		assertEquals(1, preferredLanguages.size());	
		assertEquals("en-gb", preferredLanguages.get(0));		
		
		preferredLanguages = new AcceptLanguageHeaderParser("en-gb,en;q=foo").getLanguageCodes(); // invalid q value
		assertEquals(1, preferredLanguages.size());		
		assertEquals("en-gb", preferredLanguages.get(0));
		
		preferredLanguages = new AcceptLanguageHeaderParser("en-us,en&gb").getLanguageCodes(); // invalid &
		assertEquals(1, preferredLanguages.size());		
		assertEquals("en-us", preferredLanguages.get(0));	
		
		preferredLanguages = new AcceptLanguageHeaderParser("en-us,en gb").getLanguageCodes(); // invalid space
		assertEquals(1, preferredLanguages.size());		
		assertEquals("en-us", preferredLanguages.get(0));				
	}

	@Test
	public void testGetLanguageCodesWithAcceptedSpaces() {
		List<String> preferredLanguages = new AcceptLanguageHeaderParser("en ").getLanguageCodes();
		assertEquals(1, preferredLanguages.size());
		assertEquals("en", preferredLanguages.get(0));

		preferredLanguages = new AcceptLanguageHeaderParser("en; q=0.6,fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en", preferredLanguages.get(1));
		
		preferredLanguages = new AcceptLanguageHeaderParser("en; q =0.6,fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en", preferredLanguages.get(1));
		
		preferredLanguages = new AcceptLanguageHeaderParser("en; q= 0.6,fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en", preferredLanguages.get(1));
		
		preferredLanguages = new AcceptLanguageHeaderParser("en; q = 0.6,fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en", preferredLanguages.get(1));
		
		preferredLanguages = new AcceptLanguageHeaderParser(" fr,en-gb").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());		
		assertEquals("fr", preferredLanguages.get(0));	
		assertEquals("en-gb", preferredLanguages.get(1));
		
		preferredLanguages = new AcceptLanguageHeaderParser("en-gb,fr ; q = 0.9 ").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());		
		assertEquals("en-gb", preferredLanguages.get(0));
		assertEquals("fr", preferredLanguages.get(1));
	}

	@Test
	public void testLanguageCodes() {
		List<String> preferredLanguages = new AcceptLanguageHeaderParser("fr").getLanguageCodes();
		assertEquals(1, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));

		preferredLanguages = new AcceptLanguageHeaderParser("en, fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("en", preferredLanguages.get(0));
		assertEquals("fr", preferredLanguages.get(1));

		preferredLanguages = new AcceptLanguageHeaderParser("en-us, fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("en-us", preferredLanguages.get(0));
		assertEquals("fr", preferredLanguages.get(1));

		preferredLanguages = new AcceptLanguageHeaderParser("en, en-gb, fr").getLanguageCodes();
		assertEquals(3, preferredLanguages.size());
		assertEquals("en", preferredLanguages.get(0));
		assertEquals("en-gb", preferredLanguages.get(1));
		assertEquals("fr", preferredLanguages.get(2));

		preferredLanguages = new AcceptLanguageHeaderParser("en;q=0.9, fr;q=1.0").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en", preferredLanguages.get(1));

		preferredLanguages = new AcceptLanguageHeaderParser("en-us;q=0.3, fr").getLanguageCodes();
		assertEquals(2, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en-us", preferredLanguages.get(1));

		preferredLanguages = new AcceptLanguageHeaderParser("en-us;q=0.4, en, fr").getLanguageCodes();
		assertEquals(3, preferredLanguages.size());
		assertEquals("en", preferredLanguages.get(0));
		assertEquals("fr", preferredLanguages.get(1));
		assertEquals("en-us", preferredLanguages.get(2));

		preferredLanguages = new AcceptLanguageHeaderParser("en-us;q=0.4, en;q=0.5, fr").getLanguageCodes();
		assertEquals(3, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en", preferredLanguages.get(1));
		assertEquals("en-us", preferredLanguages.get(2));

		preferredLanguages = new AcceptLanguageHeaderParser("en-us;q=1.0, en-gb;q=0.8,fr").getLanguageCodes();
		assertEquals(3, preferredLanguages.size());
		assertEquals("en-us", preferredLanguages.get(0));
		assertEquals("fr", preferredLanguages.get(1));
		assertEquals("en-gb", preferredLanguages.get(2));

		preferredLanguages = new AcceptLanguageHeaderParser("en-us;q=0.9,,en-gb;q=0.8,,,fr").getLanguageCodes();
		assertEquals(3, preferredLanguages.size());
		assertEquals("fr", preferredLanguages.get(0));
		assertEquals("en-us", preferredLanguages.get(1));
		assertEquals("en-gb", preferredLanguages.get(2));

		preferredLanguages = new AcceptLanguageHeaderParser("en-gb;q=0.7, en-us;q=0.8, fr;q=0.9, en")
				.getLanguageCodes();
		assertEquals(4, preferredLanguages.size());
		assertEquals("en", preferredLanguages.get(0));
		assertEquals("fr", preferredLanguages.get(1));
		assertEquals("en-us", preferredLanguages.get(2));
		assertEquals("en-gb", preferredLanguages.get(3));
	}
}
