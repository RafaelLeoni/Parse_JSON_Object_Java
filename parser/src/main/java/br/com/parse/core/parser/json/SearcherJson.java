package br.com.parse.core.parser.json;

import java.io.BufferedReader;
import java.io.IOException;

public final class SearcherJson {
	
  public static String find(BufferedReader reader) throws IOException{
	StringBuffer json = new StringBuffer();
    String line = null;
    while ((line = reader.readLine()) != null){
          json.append(line);
    }   
     return json.toString();
  }

}
