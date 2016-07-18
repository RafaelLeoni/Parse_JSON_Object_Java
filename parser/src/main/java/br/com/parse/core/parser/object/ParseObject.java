package br.com.parse.core.parser.object;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.parse.core.exception.ParseException;
import br.com.parse.core.exception.ReflectionException;
import br.com.parse.core.util.ReflectionUtil;
import br.com.parse.core.util.StringUtil;

public class ParseObject {
	
	private final char OPEN_OBJECT = '{';
	private final char CLOSE_OBJECT = '}';
	private final char OPEN_ARRAY = '[';
	private final char CLOSE_ARRAY = ']';
	private final char PAIR_SEPARATOR = ',';
	private final char NAME_VALUE_SEPARATOR = ':';
	private final char ASPAS = '"';
	private final char SPACE = ' ';
	
	private final char[] IGNORE_CHARS = {SPACE, ASPAS, '-'};
	
	public <T> T toObject(String json, Class<T> clazz) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		Iterator<Character> iter = StringUtil.iterator(json);
		
		while (iter.hasNext()) {
		    char c = (char) iter.next();
		    if (c == OPEN_OBJECT) {
		    	readFieldName(iter, values);
		    } else {
		        throw new ParseException("Caracter inv�lido, era esperado '{' e recebeu '" + c + "';");
		    }
		}
		
		return buildObject(values, clazz);
	}
	
	private void readFieldName(Iterator<Character> iter, Map<String, Object> values) {
		
		StringBuilder fieldName = new StringBuilder();
		
		while (iter.hasNext()) {
		    char c = (char) iter.next();
		    if (Character.isLetterOrDigit(c)) {
		        fieldName.append(c);
		    } else if (c == NAME_VALUE_SEPARATOR) {
		    	readFieldValue(iter, values, fieldName.toString());
		    	fieldName = new StringBuilder();
		    } else if (c == PAIR_SEPARATOR || c == CLOSE_ARRAY || c == CLOSE_OBJECT) {
		    	return;
		    } else if (!(Arrays.binarySearch(IGNORE_CHARS, c) >= 0)) {
		    	throw new ParseException("Caracter inv�lido, era esperado um alpha-num�rico ou ':' e recebeu '" + c + "';");
		    }
		}
		
	}

	private void readFieldValue(Iterator<Character> iter, Map<String, Object> values, String fieldName) {
		
		StringBuilder fieldValue = new StringBuilder();
		
		while (iter.hasNext()) {
		    char c = (char) iter.next();
		    if (Character.isLetterOrDigit(c)) {
		    	fieldValue.append(c);
		    } else if (c == OPEN_OBJECT) {
		    	Map<String, Object> newValues = new HashMap<String, Object>();
		    	readFieldName(iter, newValues);
		    	values.put(fieldName, newValues);
		    	return;
		    } else if (c == OPEN_ARRAY) {
		    	readArrayFieldValue(iter, values, fieldName);
		    	return;
		    } else if ((c == PAIR_SEPARATOR) || (c == CLOSE_OBJECT)) {
                values.put(fieldName, fieldValue);
                return;
            } else if (!(Arrays.binarySearch(IGNORE_CHARS, c) >= 0)) {
		    	throw new ParseException("Caracter '" + c + "' inv�lido;");
		    }
		}
		
	}
	
	private void readArrayFieldValue(Iterator<Character> iter, Map<String, Object> values, String fieldName) {
		
		List<Map<String, Object>> arrayValues = new ArrayList<Map<String, Object>>();
		
		while (iter.hasNext()) {
			char c = (char) iter.next();
			if (c == OPEN_OBJECT) {
				Map<String, Object> newValues = new HashMap<String, Object>();
				readFieldName(iter, newValues);
				arrayValues.add(newValues);
				values.put(fieldName, arrayValues);
			} else if (c == PAIR_SEPARATOR || c == CLOSE_OBJECT) {
		    	return;
			} else if (c != OPEN_OBJECT && c != SPACE) {
		    	throw new ParseException("Caracter '" + c + "' inv�lido;");
		    }
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> T buildObject(Map<String, Object> values, Class<T> clazz) {
		
		T object = null;
		try {
			object = ReflectionUtil.newInstance(clazz);
			for (String fieldName : values.keySet()) {
				Object fieldValue = values.get(fieldName);
				Field field = ReflectionUtil.getFieldByName(clazz, fieldName);
				
				if (Map.class.isAssignableFrom(fieldValue.getClass())) {
					Object result = buildObject((Map<String, Object>) fieldValue, field.getType());
					ReflectionUtil.setValue(object, fieldName, result);
				} else if (List.class.isAssignableFrom(fieldValue.getClass())) {
					Class<?> type = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
					Collection collection = new ArrayList();
					for (Map<String, Object> map : (List<Map<String, Object>>) fieldValue) {
						collection.add(buildObject(map, type));
					}
					ReflectionUtil.setValue(object, fieldName, collection);
				} else {
					String value = fieldValue.toString();
					ReflectionUtil.setValue(object, fieldName, value);
				}
				
			}
		} catch (ReflectionException e) {
			throw new ParseException(e);
		}
		
		return object;
	}
	
}