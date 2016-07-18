package br.com.parse.test;

import java.util.ArrayList;
import java.util.List;

import br.com.parse.core.exception.ParseException;
import br.com.parse.core.parser.Parser;
import br.com.parse.entity.Endereco;
import br.com.parse.entity.Telefone;
import br.com.parse.entity.Usuario;

public class TestToJson {
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario("Master", true, 15);
		Endereco endereco = new Endereco("Tijuca", 72, 302L);
		List<Telefone> telefones = new ArrayList<Telefone>();
			telefones.add(new Telefone("2222-3333"));
			telefones.add(new Telefone("9 4444-5555"));
		
		usuario.setEndereco(endereco);
		usuario.setTelefones(telefones);
		
		try {
			System.out.println(new Parser().toJson(usuario));
			System.out.println(new Parser().toJson(telefones));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}