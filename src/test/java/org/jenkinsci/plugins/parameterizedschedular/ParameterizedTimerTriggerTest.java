package org.jenkinsci.plugins.parameterizedschedular;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ParameterizedTimerTriggerTest {

	@Test
	public void ctor() throws Exception {
		String parameterizedSpecification = "* * * * *%foo=bar";
		ParameterizedTimerTrigger testObject = new ParameterizedTimerTrigger(parameterizedSpecification);

		assertSame(parameterizedSpecification, testObject.getParameterizedSpecification());

	}
}
