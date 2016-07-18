package br.com.parse.core.build;

import br.com.parse.core.parser.object.ParseObject;

public class BuildTargetClass implements Build{

	private String json;

	public BuildTargetClass(String json) {
		this.json = json;
	}
	
	public <T> T in(Class<T> clazz){
		ParseObject parser = new ParseObject();
		return  parser.toObject(this.json, clazz);
		
	}
}
