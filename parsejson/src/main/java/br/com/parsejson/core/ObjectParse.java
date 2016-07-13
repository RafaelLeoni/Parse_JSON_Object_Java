package br.com.parsejson.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.NoSuchElementException;

import br.com.parsejson.exception.ParseException;
import br.com.parsejson.util.ParseUtil;

public class ObjectParse {
	
	private String json;
	private int cursor;
	
	public ObjectParse(String json) {
		this.json = json;
		cursor = 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> E toObject(Class<E> classType) throws ParseException {
		
		try {
			Object object = classType.newInstance();
			Collection collection = null;
			Field field = null;
			Type type = null;
			
			String nextParameter = "";
			
			while (hasNext()) {
				nextParameter = nextParameter();
				
				if (nextParameter.matches("[a-zA-Z0-9]+")) {
					if (ParseUtil.isClassField(nextParameter, classType)) {
						field = classType.getDeclaredField(nextParameter);
					}
				} else if (field != null) {
					field.setAccessible(true);
					if (nextParameter.equals(":")) {
						nextParameter = nextParameter();
						if (nextParameter.equals("\"")) {
							field.set(object, ParseUtil.toValueType(nextParameter(), field.getType()));
						} else if (nextParameter.matches("[0-9]+")) {
							field.set(object, ParseUtil.toValueType(nextParameter, field.getType()));
						} else if (nextParameter.equals("null")) {
							field.set(object, null);
						} else if (nextParameter.equals("true") || nextParameter.equals("false")) {
							field.set(object, nextParameter.equals("true"));
						} else if (nextParameter.equals("{")) {
							field.set(object, toObject(field.getType()));
						} else if (nextParameter.equals("[")) {
							collection = ParseUtil.getCollectionInstance(field);
							type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
						}
					} else if (nextParameter.equals("{")) {
						collection.add(toObject((Class<E>) type));
					} else if (nextParameter.equals("}")) {
						return (E) object;
					} else if (nextParameter.equals("]")) {
						field.set(object, collection);
					}
				}
			} 
			return (E) object;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ParseException(e);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ParseException(e);
		}
	}
	
	private String nextChar() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return ((Character) json.charAt(cursor++)).toString();
	}
	
	private boolean hasNext() {
		return cursor < json.length();
	}
	
	private String nextParameter() {
		String nextParameter = "";
		String parameter = nextChar();
		if (parameter.equals("{") || parameter.equals("\"") || parameter.equals(":") || parameter.equals(",")
				|| parameter.equals("[") || parameter.equals("]") || parameter.equals("}")) {
			return parameter;
		} else if (parameter.equals(" ")) {
			return nextParameter();
		} else {
			while (parameter.matches("[^{\":,}]")) {
				nextParameter += parameter;
				parameter = nextChar();
			}
			return nextParameter;
		}
	}

}