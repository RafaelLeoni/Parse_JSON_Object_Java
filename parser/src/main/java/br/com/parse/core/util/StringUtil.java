package br.com.parse.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringUtil {
	
	/**
	 * Converte uma String para um iterator
	 * @param string
	 * @return Iterator da string passada.
	 */
	public static Iterator<Character> iterator(String string) {
		List<Character> chars = new ArrayList<Character>();
		if (null != string || !isEmpty(string)) {
			for (char c : string.toCharArray()) {
				chars.add(c);
			}			
		}
		return chars.iterator();
	}
	
	/**
     * Verifica se uma string � nula ou est� vazia.
     * @param str string a ser verificada
     * @return verdadeiro se a string passada for nula ou vazia
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || ("".equals(str)));
    }
	
	/**
     * Converte a primeira letra para mai�scula.
     * @param str String a qual a primeira letra ser� convertida para mai�scula
     * @return String com a primeira letra em mai�scula
     */
	public static String capitalize(String str) {
		if (StringUtil.isEmpty(str)) {
            return str;
        }
        if (str.length() > 1) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        } else {
            return str.toUpperCase();
        }
	}
	
	public static String convertToString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

}
