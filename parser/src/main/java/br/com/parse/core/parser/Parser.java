package br.com.parse.core.parser;

import java.io.Writer;

import br.com.parse.core.build.Build;
import br.com.parse.core.build.BuildTargetClass;
import br.com.parse.core.parser.json.ParseJson;


/**
 * Simple Class to Parser Json and Make Objects
 */
public final class Parser {	
	public String toJson(Object object){
		return new ParseJson().toJson(object);
	}
	public Build toObject(String json){
		return new BuildTargetClass(json);
	}
	
/*	public static String readerJson(BufferedReader reader){
		try {
			return SearcherJson.find(reader);
		} catch (IOException e) {
			throw new ParseException("N�o foi poss�vel fazer a leitura da Stream : ", e);
		}
	}*/
	
   public void writerJson(Writer writer){
	   
   }
}
