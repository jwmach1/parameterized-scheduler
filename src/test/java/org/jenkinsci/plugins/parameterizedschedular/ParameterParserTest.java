package org.jenkinsci.plugins.parameterizedschedular;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Test;

public class ParameterParserTest {

	@Test
	public void test_nullReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		assertEquals(new HashMap<String, String>(), testObject.parse(null));
	}

	@Test
	public void test_EmptyStringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		assertEquals(new HashMap<String, String>(), testObject.parse(""));
		assertEquals(new HashMap<String, String>(), testObject.parse("     "));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Malformed_NoEquals_StringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		assertEquals(new HashMap<String, String>(), testObject.parse("namevalue"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Malformed_ExtraSemicolon_StringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("name", "value");
		assertEquals(expected, testObject.parse("name=value;;"));
	}

	@Test
	public void test_OneParamStringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("name", "value");
		assertEquals(expected, testObject.parse("name=value"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_TrimsSpacesStringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("name", "value");
		testObject.parse(" name = value; ;;");
	}

	@Test
	public void test_TwoParamsStringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("name", "value");
		expected.put("name2", "value2");
		assertEquals(expected, testObject.parse("name2=value2;name=value"));
	}

	@Test
	public void checkSanity_HappyPath() throws Exception {
		ParameterParser testObject = new ParameterParser();

		assertNull(testObject.checkSanity("* * * * *%name=value"));
	}

	@Test
	public void checkSanity_TrailingSemiColon_IsTrimmed() throws Exception {
		ParameterParser testObject = new ParameterParser();

		assertNull(testObject.checkSanity("* * * * *%env=eight;freckled=flase;frecked=false;"));
	}

	@Test
	public void checkSanity_MoreThanOnePercent() throws Exception {
		ParameterParser testObject = new ParameterParser();

		assertEquals(Messages.ParameterizedTimerTrigger_MoreThanOnePercent(),
				testObject.checkSanity("* * * * *%name=value;%fred=barney"));
	}

	@Test
	public void checkSanity_NoParaetersIsNoBigDeal() throws Exception {
		ParameterParser testObject = new ParameterParser();

		assertNull(testObject.checkSanity("* * * * *%"));
		assertNull(testObject.checkSanity("* * * * *"));
	}

	@Test
	public void checkSanity_duplicateParamName() throws Exception {
		ParameterParser testObject = new ParameterParser();

		testObject.checkSanity("* * * * *%name=value;name=value2");
	}

	@Test
	public void checkSanity_UnmatchedEquals() throws Exception {
		ParameterParser testObject = new ParameterParser();

		testObject.checkSanity("* * * * *%name=value;name2=");
	}

}
