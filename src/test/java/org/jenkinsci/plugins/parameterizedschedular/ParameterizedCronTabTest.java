package org.jenkinsci.plugins.parameterizedschedular;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hudson.scheduler.CronTab;

import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class ParameterizedCronTabTest {

	@Test
	public void ctor_happyPath() throws Exception {
		Map<String, String> parameters = Maps.newHashMap();
		parameters.put("one", "onevalue");
		CronTab testCronTab = new CronTab("* * * * *");
		ParameterizedCronTab testObject = new ParameterizedCronTab(testCronTab, parameters);

		assertEquals(parameters, testObject.getParameterValues());
		assertTrue(testObject.check(new GregorianCalendar()));
		assertNull(testObject.checkSanity());
	}

}
