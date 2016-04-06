package parse;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class JSONParse {
	
	StringBuilder json;
	Object object;
	
	public JSONParse(Object object) {
		this.object = object;
	}
	
	public String toJson() throws IllegalArgumentException, IllegalAccessException {
		json = new StringBuilder("{");
		
		String name = ParseUtil.getObjectName(object);
		buildJson(name, object);
		
		json.append("}");
		
		return json.toString();
	}
	
	private StringBuilder buildJson(String name, Object object) throws IllegalArgumentException, IllegalAccessException {
		
		if (object != null) {
			
			buildJsonHeader(name);
			
			if (ParseUtil.isInstanceOfCollection(object.getClass())) {
				json.append("[");
				Iterator<?> objectIterator = ((Collection<?>) object).iterator();
				while (objectIterator.hasNext()) {
					Object objectElement = objectIterator.next();

					buildJsonBody(objectElement);

					if (objectIterator.hasNext()) {
						json.append(",");
					}
				}
				json.append("]");
			} else {
				buildJsonBody(object);
			} 
		} else {
			json.deleteCharAt(json.lastIndexOf(","));
		}
		
		return json;
	}
	
	private StringBuilder buildJsonHeader(String name) {
		json.append("\"").append(name).append("\":");
		
		return json;
	}
	
	private StringBuilder buildJsonBody(Object object) throws IllegalArgumentException, IllegalAccessException {
		Class<?> classType = object.getClass();
		
		json.append("{");
		
		Iterator<Field> fields = Arrays.asList(classType.getDeclaredFields()).iterator();
		while (fields.hasNext()) {
			Field field = fields.next();
			
			field.setAccessible(true);
			if (field.getType().isPrimitive() || ParseUtil.isWrapper(field.getType())) {
				json.append("\"").append(field.getName()).append("\":\"").append(field.get(object)).append("\"");
			} else {
				buildJson(field.getName(), field.get(object));
			}
			
			if (fields.hasNext()) {
				json.append(",");
			}
			
		}
		json.append("}");
		
		return json;
	}
	
}
