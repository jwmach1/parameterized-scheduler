package org.jenkinsci.plugins.parameterizedschedular;

import java.lang.reflect.Field;

public class FieldAccessor {

	public static Object access(Object instance, String fieldName) {
		
		try {
			Field field = instance.getClass().getDeclaredField(fieldName);
			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);
			Object value = field.get(instance);
			field.setAccessible(isAccessible);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
