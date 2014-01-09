package org.jenkinsci.plugins.parameterizedschedular;

import java.lang.reflect.Field;

/**
 * lets work around some final classes I couldn't subclass, but don't want to duplicate everything
 * 
 * @author jameswilson
 *
 */
public class FieldAccessor {

	@SuppressWarnings("unchecked")
	public <T> T access(Object instance, String fieldName) {
		
		try {
			Field field = instance.getClass().getDeclaredField(fieldName);
			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);
			Object value = field.get(instance);
			field.setAccessible(isAccessible);
			return (T)value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
