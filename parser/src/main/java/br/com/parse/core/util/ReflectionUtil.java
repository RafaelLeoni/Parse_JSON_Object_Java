package br.com.parse.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.parse.core.exception.ReflectionException;


@SuppressWarnings("rawtypes")
public class ReflectionUtil {
	
	/**
     * Retorna todas as propriedades de uma classe.
     * @param clazz Classe e ser varrida
     * @return Lista de todos os campos encontrados
     */
	public static List<Field> getDeclaredFields(Class clazz) {
        List<Field> listFields = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            listFields.add(field);
        }
        return listFields;
    }
	
	/**
     * M�todo para obter o valor de um campo invocando a reflection api.
     * @param obj Objeto de onde ser� buscado o valor
     * @param fieldName nome do atribudo que ser� buscado o valor
     * @return Objeto representando o valor do atributo
     * @throws ReflectionException em qualquer exce��o relativa a Reflection
     */
    public static Object getValue(Object obj, String fieldName) throws ReflectionException {
        Method methodGet = null;
        String methodGetName = "get" + StringUtil.capitalize(fieldName);
        try {
            methodGet = obj.getClass().getMethod(methodGetName);
            return methodGet.invoke(obj);
        } catch (Exception e) {
            throw new ReflectionException("N�o foi poss�vel encontrar o m�todo: [" + methodGetName + "] na classe [" + obj.getClass().getSimpleName() + "]", e);
        }
    }
    
    /**
     * M�todo para setar o valor de um campo invocando a reflection api.
     * @param object Objeto de onde ser� setado o valor
     * @param fieldName nome do atribudo que ser� setado o valor
     * @return Objeto representando o valor do atributo
     * @throws ReflectionException em qualquer exce��o relativa a Reflection
     */
    public static void setValue(Object object, String fieldName, String fieldValue) throws ReflectionException {
        Method methodSet = null;
        String methodSetName = "set" + StringUtil.capitalize(fieldName);
        try {
        	Class<?> type = getFieldByName(object.getClass(), fieldName).getType();
            methodSet = object.getClass().getMethod(methodSetName, type);
            Object value = AssignableUtil.convertToObject(fieldValue, type);
            methodSet.invoke(object, value);
        } catch (Exception e) {
        	throw new ReflectionException("N�o foi poss�vel encontrar o m�todo: [" + methodSetName + "] na classe [" + object.getClass().getSimpleName() + "]", e);
		}
    }
    
    /**
     * M�todo para setar o valor de um campo invocando a reflection api.
     * @param object Objeto de onde ser� setado o valor
     * @param fieldName nome do atribudo que ser� setado o valor
     * @return 
     * @return Objeto representando o valor do atributo
     * @throws ReflectionException em qualquer exce��o relativa a Reflection
     */
    public static void setValue(Object object, String fieldName, Object fieldValue) throws ReflectionException {
    	Method methodSet = null;
        String methodSetName = "set" + StringUtil.capitalize(fieldName);
        try {
        	Class<?> type = getFieldByName(object.getClass(), fieldName).getType();
            methodSet = object.getClass().getMethod(methodSetName, type);
            methodSet.invoke(object, fieldValue);
        } catch (Exception e) {
        	throw new ReflectionException("N�o foi poss�vel encontrar o m�todo: [" + methodSetName + "] na classe [" + object.getClass().getSimpleName() + "]", e);
		}
    }
    
    /**
     * Verifica a existencia do m�todo get de um Field.
     * @param field Reflection Field  
     * @return true quando existir o campo
     */
    public static boolean hasGetMethod(Field field) {
        boolean retorno = false;
        String methodGetName = "get" + StringUtil.capitalize(field.getName());
        try {
            field.getDeclaringClass().getMethod(methodGetName);
            retorno = true;
        } catch (NoSuchMethodException  e) {
            retorno = false;
        }
        return retorno;
    }
    
    /**
     * Cria nova instancia de uma determinada classe.
     * @param <T> tipo da classe a ser criada
     * @param clazz classe a ser instanciada
     * @return Objeto de referencia a classe
     * @throws ReflectionException no caso de ocorrer algum problema instanciando a classe
     */
    public static <T> T newInstance(Class<T> clazz) throws ReflectionException {
        T object = null;
        try {
        	object = clazz.newInstance();
        } catch (Exception e) {
            String msg = "N�o foi poss�vel instanciar a classe [" + clazz.getSimpleName() + "]";
            throw new ReflectionException(msg, e);
        }
        return object;
    }
    
    /**
     * Retorna um java.lang.reflect.Field buscando em uma classe pelo seu nome.
     * @param clazz classe onde ser� localizado o Field
     * @param fieldName nome do campo
     * @return Field procurado
     * @throws ReflectionException em qualquer erro de reflexao
     */
    public static Field getFieldByName(final Class clazz, String fieldName) throws ReflectionException {
        try {
        	Field field = clazz.getDeclaredField(fieldName);
        	return field;
        } catch (NoSuchFieldException e) {
        	throw new ReflectionException("N�o foi poss�vel encontrar o m�todo: [" + fieldName + "] na classe [" + clazz.getName() + "]", e);
        }
    }
    
}
