package br.com.parse.test;

import br.com.parse.core.parser.Parser;
import br.com.parse.entity.Usuario;

public class TestToObject {
	
	public static void main(String[] args) {
		
		String json = "{"
						+ "\"endereco\": {"
											+ "\"bairro\": \"centro\", "
											+ "\"numero\": 15, "
											+ "\"complemento\": 4"
										+ "}, "
						+ "\"telefones\": ["
											+ "{\"numero\": \"7515-5624\"}, "
											+ "{\"numero\": \"9 9562-8542\"} "
										+ "], "	
						+ "\"nome\": \"master\", "
						+ "\"ativo\": true, "
						+ "\"idade\": 15"
					+ "}";
		Parser parserToObject = new Parser();
		Usuario userParser = parserToObject.toObject(json).in(Usuario.class);
		System.out.println(userParser.toString());
	
	}
	
}
