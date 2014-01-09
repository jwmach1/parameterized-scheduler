package org.jenkinsci.plugins.parameterizedschedular;

import static org.junit.Assert.assertEquals;

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
	}
	
	@Test
	public void test_Malformed_NoEquals_StringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();
		
		assertEquals(new HashMap<String, String>(), testObject.parse("namevalue"));
	}
	@Test
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
	
	@Test
	public void test_TrimsSpacesStringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();
		
		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("name", "value");
		assertEquals(expected, testObject.parse(" name = value; ;;"));
	}

	@Test
	public void test_TwoParamsStringReturns_emptyMap() {
		ParameterParser testObject = new ParameterParser();

		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("name", "value");
		expected.put("name2", "value2");
		assertEquals(expected, testObject.parse("name2=value2;name=value"));
	}

}
