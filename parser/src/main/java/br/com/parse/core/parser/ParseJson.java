package br.com.parse.core.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.parse.core.exception.ParseException;
import br.com.parse.core.exception.ReflectionException;
import br.com.parse.core.util.AssignableUtil;
import br.com.parse.core.util.ReflectionUtil;
import br.com.parse.core.util.StringUtil;

public class ParseJson {
	
	private final char OPEN_OBJECT = '{';
	private final char CLOSE_OBJECT = '}';
	private final char OPEN_ARRAY = '[';
	private final char CLOSE_ARRAY = ']';
	private final char PAIR_SEPARATOR = ',';
	private final char NAME_VALUE_SEPARATOR = ':';
	private final char ASPAS = '"';
	private final char SPACE = ' ';
	private final String NULO = "null";
	
	private StringBuilder json;
	
	{
		json = new StringBuilder();
	}
	
	public String toJson(Object object) throws ParseException {
		
		json.append(OPEN_OBJECT);
		
		writeFieldName(object.getClass().getSimpleName());
		writeJson(object);
		
		json.append(CLOSE_OBJECT);
		
		return json.toString();
	}
	
	public String toJson(Collection<?> collection) {
		
		String collectionTypeName = AssignableUtil.getCollectionTypeName(collection);
		if (collectionTypeName != null) {
			json.append(OPEN_OBJECT);
		
			writeFieldName(collectionTypeName);
			collectionIterator(collection);

			json.append(CLOSE_OBJECT);
		} else {
			throw new ParseException("A cole��o n�o pode estar vazia ou nula");
		}
		return json.toString();
	}
	
	private void writeJson(Object object) {
		List<Field> fields = ReflectionUtil.getDeclaredFields(object.getClass());
		boolean firstField = true;
		json.append(OPEN_OBJECT);
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers()) || !ReflectionUtil.hasGetMethod(field)) {
				continue;
			}
			
			if (firstField) {
				firstField = false;
			} else {
				json.append(PAIR_SEPARATOR)
					.append(SPACE);
			}
			
			writeFieldName(field.getName());
			
			if (AssignableUtil.isAssignableFromPrimitiveOrWrapperType(field)) {
				writeFieldValue(object, field);
			} else if (Collection.class.isAssignableFrom(field.getType())) {
				writeCollectionValue(object, field.getName());
			} else {
				writeObjectValue(object, field.getName());
			}
		}
		json.append(CLOSE_OBJECT);
	}
	
	private void writeFieldName(String objectName) {
		json.append(ASPAS)
			.append(objectName)
			.append(ASPAS)	
			.append(NAME_VALUE_SEPARATOR)
			.append(SPACE);
	}	
	
	private void writeFieldValue(Object object, Field field) {
		try {
			Object o = ReflectionUtil.getValue(object, field.getName());
	        String value = StringUtil.convertToString(o);
	        if (o == null) {
	            json.append(NULO);
	        } else if (String.class.isAssignableFrom(field.getType())) {
	        	json.append(ASPAS)
	        		.append(value)
	        		.append(ASPAS);
	        } else {
	            json.append(value);
	        }
		} catch (ReflectionException e) {
			throw new ParseException(e);
		}
	}
	
	private void writeObjectValue(Object object, String fieldName) {
		try {
			Object o = br.com.parse.core.util.ReflectionUtil.getValue(object, fieldName);
			if (o == null) {
				json.append(NULO);
			} else {
				writeJson(o);
			}
		} catch (ReflectionException e) {
			throw new ParseException(e);
		}
	}
	
	private void writeCollectionValue(Object object, String fieldName) {
		try {
			Collection<?> collection = (Collection<?>) br.com.parse.core.util.ReflectionUtil.getValue(object, fieldName);
			if (collection == null) {
				json.append(NULO);
			} else {
				collectionIterator(collection);
			}
		} catch (ReflectionException e) {
			throw new ParseException(e);
		}
	}
	
	private void collectionIterator(Collection<?> collection) {
		Iterator<?> iter = collection.iterator();
		json.append(OPEN_ARRAY);
		while (iter.hasNext()) {
			writeJson(iter.next());
            if (iter.hasNext()) {
                json.append(PAIR_SEPARATOR)
                	.append(SPACE);
            }
        }
		json.append(CLOSE_ARRAY);
	}
	
}