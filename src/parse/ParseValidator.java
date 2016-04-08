package parse;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import exception.ParseException;

public class ParseValidator {
	
	private String json;
	
	public ParseValidator(String json) {
		this.json = json.replace(" ", "");
	}
	
	public void validate() throws ParseException {
		CharacterIterator chars = new StringCharacterIterator(json);
		
		if (json.charAt(0) != '{') {
			throw new ParseException("Invalid json string, the first char of json must be '{'");
		}
		
		while (chars.current() != CharacterIterator.DONE) {
			System.out.println(chars.next());
		}
		
	}
	
	public static void main(String[] args) {
		
		String json = "{\"usuario\":{\"nome\":\"Master\",\"login\":\"master\",\"senha\":\"teste\"}}";
		
		try {
			new ParseValidator(json).validate();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
