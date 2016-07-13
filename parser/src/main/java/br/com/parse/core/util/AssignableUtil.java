package br.com.parse.core.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

public class AssignableUtil {
	
	/**
     * Verifica se o tipo pertence aos tipos padr�es de copia de propriedades. eg. String, Long, long, etc...
     * @param field campo a ser testado.
     * @return true caso perten�a e false caso contr�rio.
     */
    public static boolean isAssignableFromPrimitiveOrWrapperType(Field field) {
        if (Integer.class.isAssignableFrom(field.getType())       || int.class.isAssignableFrom(field.getType())				||
        	Long.class.isAssignableFrom(field.getType())          || long.class.isAssignableFrom(field.getType())				||
        	Float.class.isAssignableFrom(field.getType())         || float.class.isAssignableFrom(field.getType())				||
        	Double.class.isAssignableFrom(field.getType())        || double.class.isAssignableFrom(field.getType())				||
        	Boolean.class.isAssignableFrom(field.getType()) 	  || boolean.class.isAssignableFrom(field.getType())			||
        	java.sql.Date.class.isAssignableFrom(field.getType()) || java.util.Date.class.isAssignableFrom(field.getType())		||
        	java.sql.Time.class.isAssignableFrom(field.getType()) || java.sql.Timestamp.class.isAssignableFrom(field.getType())	||
        	BigDecimal.class.isAssignableFrom(field.getType())    || String.class.isAssignableFrom(field.getType())				||
        	Enum.class.isAssignableFrom(field.getType()) ) {
        	
        	return true;
        }
        return false;
    }
    
    public static String getCollectionTypeName(Collection<?> collection) {
    	if (collection != null) {
			Iterator<?> iter = collection.iterator();
			if (iter.hasNext()) {
				Object o = iter.next();
				return o.getClass().getSimpleName();
			} 
		}
    	return null;
    }
    
    public static Object convertToObject(String fieldValue, Class<?> type) {
    	
    	if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class)) {
    		return Integer.parseInt(fieldValue);
    	} else if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)) {
    		return Long.parseLong(fieldValue);
    	} else if (type.isAssignableFrom(Float.class) || type.isAssignableFrom(float.class)) {
    		return Float.parseFloat(fieldValue);
    	} else if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
    		return Double.parseDouble(fieldValue);
    	} else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
    		return Boolean.parseBoolean(fieldValue);
    	} else if(type.isAssignableFrom(java.sql.Date.class)) {
    		return java.sql.Date.valueOf(fieldValue);
    	} else if(type.isAssignableFrom(java.sql.Time.class)) {
    		return java.sql.Time.valueOf(fieldValue);
    	} else if(type.isAssignableFrom(java.sql.Time.class)) {
     		return java.sql.Timestamp.valueOf(fieldValue);
     	} else {
    	 	return fieldValue;
    	}
    }

}
