package com.temenos.interaction.media.odata.xml.atom;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.temenos.interaction.core.entity.EntityProperty;

public class AtomXMLProviderHelperTest {

	@Test
	public void testCheckAndConvertDateTimeToUTCFromNonUTCDate() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT-8:00"));
		EntityProperty property = new EntityProperty("dateOfBirth",
				format.parse("2015-12-31T16:10:00"));
		assertEquals("2016-01-01T00:10:00",
				AtomXMLProviderHelper.checkAndConvertDateTimeToUTC(property));
	}

	@Test
	public void testCheckAndConvertDateTimeToUTCFromUTCDate() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		EntityProperty property = new EntityProperty("dateOfBirth",
				format.parse("2015-12-31T16:10:00"));
		assertEquals("2015-12-31T16:10:00",
				AtomXMLProviderHelper.checkAndConvertDateTimeToUTC(property));
	}

	@Test
	public void testCheckAndConvertDateTimeToUTCFromLocalJodaDate()
			throws Exception {
		DateTimeZone defaultZone = DateTimeZone.getDefault();
		LocalDateTime ldt = new LocalDateTime(2015, 12, 31, 16, 10, 0);
		DateTimeZone.setDefault(DateTimeZone.UTC);
		EntityProperty property = new EntityProperty("dateOfBirth", ldt);
		DateTime utcTime = ldt.toDateTime();
		String expected = "2015-12-31T" + utcTime.hourOfDay().get() + ":"
				+ utcTime.minuteOfHour().get() + ":"
				+ utcTime.secondOfMinute().get() + "0";
		assertEquals(expected,
				AtomXMLProviderHelper.checkAndConvertDateTimeToUTC(property));
		DateTimeZone.setDefault(defaultZone);
	}

	@Test
	public void testCheckAndConvertDateTimeToUTCFromInvalidType()
			throws Exception {
		try {
			AtomXMLProviderHelper
					.checkAndConvertDateTimeToUTC(new EntityProperty(
							"dateOfBirth", "some string object"));
			fail("RuntimeException should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
		}
	}
}
