package org.jenkinsci.plugins.parameterizedschedular;

import java.util.Map;

import com.google.common.collect.Maps;

public class ParameterParser {

	public Map<String, String> parse(String formattedString) {
		Map<String, String> result = Maps.newHashMap();
		if (formattedString == null) {
			return result;
		}
		String[] parameters = formattedString.split(";");
		for (String parameter : parameters) {
			String[] keyvalue = parameter.split("=");
			if (keyvalue.length == 2) {
				result.put(keyvalue[0].trim(), keyvalue[1].trim());
			}
		}

		return result;
	}
}
