package parse;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class ObjectParse {
	
	private String json;
	private int cursor;
	
	public ObjectParse(String json) {
		this.json = json;
		cursor = 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object toObject(Class<?> classType) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException  {
		
		Object object = classType.newInstance();
		Collection collection = null;
		Field field = null;
		Type type = null;
		
		String nextParameter = "";
		
		while (hasNext()) {
			nextParameter = nextParameter();
			
			if (nextParameter.matches("[a-zA-Z0-9 ]+")) {
				if (ParseUtil.isClassField(nextParameter, classType)) {
					field = classType.getDeclaredField(nextParameter);
				}
			} else if (field != null) {
				field.setAccessible(true);
				if (nextParameter.equals(":")) {
					nextParameter = nextParameter();
					if (nextParameter.equals("\"")) {
						field.set(object, ParseUtil.toValueType(nextParameter(), field.getType()));
					} else if (nextParameter.equals("{")) {
						field.set(object, toObject(field.getType()));
					} else if (nextParameter.equals("[")) {
						collection = ParseUtil.getCollectionInstance(field);
						type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
					}
				} else if (nextParameter.equals("{")) {
					collection.add(toObject((Class<?>) type));
				} else if (nextParameter.equals("}")) {
					return object;
				} else if (nextParameter.equals("]")) {
					field.set(object, collection);
				}
			}
		}
		return object;
	}
	
	private String nextChar() {
		Character c = ' ';
		for (;cursor < json.length();) {
			c = json.charAt(cursor);
			cursor++;
			return c.toString();
		}
		return c.toString();
	}
	
	private boolean hasNext() {
		if (cursor < json.length()) {
			return true;
		}
		return false;
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
