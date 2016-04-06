package main;

import java.util.ArrayList;
import java.util.List;

import entity.Endereco;
import entity.Telefone;
import entity.Usuario;
import parse.JSONParse;

public class MainJSON {
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario("Master", "master", "teste");
			usuario.setEndereco(new Endereco("Centro", "Buenos Aires", 15, "5º Andar"));
			usuario.addTelefone(new Telefone("3213-9452"));
			usuario.addTelefone(new Telefone("2271-2181"));
			
		List<Telefone> telefones = new ArrayList<>();
			telefones.add(new Telefone("3213-9452"));
			telefones.add(new Telefone("2271-2181"));
			telefones.get(0).setUsuario(new Usuario("Master", "master", "teste"));
		
		JSONParse usuarioParse = new JSONParse(usuario);
		JSONParse telefoneParse = new JSONParse(telefones);
		try {
			 System.out.println(usuarioParse.toJson());
			 System.out.println(telefoneParse.toJson());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

}
