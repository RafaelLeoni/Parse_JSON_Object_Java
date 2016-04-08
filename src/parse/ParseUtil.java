package parse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParseUtil {
	
	static boolean isString(Class<?> classType) {
		Set<Class<?>> set = new HashSet<Class<?>>();
	        set.add(Character.class);
	        set.add(String.class);
	    return set.contains(classType);
	}
	
	static boolean isNumberOrBoolean(Class<?> classType) {
		Set<Class<?>> set = new HashSet<Class<?>>();
			set.add(Boolean.class);
			set.add(Byte.class);
	        set.add(Short.class);
	        set.add(Integer.class);
	        set.add(Long.class);
	        set.add(Float.class);
	        set.add(Double.class);
	    return set.contains(classType);
	}
	
	@SuppressWarnings("rawtypes")
	static boolean isInstanceOfCollection(Class classType) {
		return Collection.class.isAssignableFrom(classType);
	}
	
	@SuppressWarnings("rawtypes")
	static boolean isInstanceOfMap(Class classType) {
		return Map.class.isAssignableFrom(classType);
	}
	
	static String getObjectName(Object object) {
		String name;
		if (isInstanceOfCollection(object.getClass())) {
			Iterator<?> objectIterator = ((Collection<?>) object).iterator();
			Object objectElement = objectIterator.next();
			name = objectElement.getClass().getName().toLowerCase();
			name = name.replace(objectElement.getClass().getPackage().getName() + ".", "");
		} else {
			name = object.getClass().getName().toLowerCase();
			name = name.replace(object.getClass().getPackage().getName() + ".", "");
		}
		return name;
	}
	
	static Object toValueType(String value, Class<?> classType) {
		if (classType.equals(Boolean.class)) {
			return Boolean.valueOf(value);
		} else if (classType.equals(String.class)) {
			return String.valueOf(value);
		} else if (classType.equals(Byte.class)) {
			return Byte.valueOf(value);
		} else if (classType.equals(Short.class)) {
			return Short.valueOf(value);
		} else if (classType.equals(Integer.class)) {
			return Integer.valueOf(value);
		} else if (classType.equals(Long.class)) {
			return Long.valueOf(value);
		} else if (classType.equals(Float.class)) {
			return Float.valueOf(value);
		} else if (classType.equals(Double.class)) {
			return Double.valueOf(value);
		}
		return null;
	}
	
	static boolean isClassField(String name, Class<?> classType) {

		List<String> allFields = new ArrayList<String>(); 
		
		Iterator<Field> fields = Arrays.asList(classType.getDeclaredFields()).iterator();
		while (fields.hasNext()) {
			Field field = fields.next();
			
			allFields.add(field.getName());
		}
		
		return allFields.contains(name);
	}
	
	@SuppressWarnings("rawtypes")
	static Collection getCollectionInstance(Field field) {
		Collection collection = null;
		if (field.getType().equals(List.class)) {
			collection = new ArrayList();
		} else if (field.getType().equals(Set.class)) {
			collection = new HashSet();
		}
		return collection;
	}
	
}