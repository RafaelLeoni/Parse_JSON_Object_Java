package main;

import java.util.ArrayList;
import java.util.List;

import entity.Telefone;
import entity.Usuario;
import exception.ParseException;
import parse.JSONParse;
import parse.ObjectParse;

public class MainObjectList {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Telefone> telefones = new ArrayList<>();
			telefones.add(new Telefone("3213-9452"));
			telefones.add(new Telefone("2271-2181"));
			telefones.get(0).setUsuario(new Usuario("Master", "master", "teste"));
		
		JSONParse telefoneParse = new JSONParse(telefones);
		
		String json = "";
		try {
			json = telefoneParse.toJson();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ObjectParse telefoneParse2 = new ObjectParse(json);
		List<Telefone> listaTelefone = null;
		try {
			listaTelefone = (List<Telefone>) telefoneParse2.toObject(List.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(json);
		for (Telefone t : listaTelefone) {
			System.out.println("-- Telefone --");
			System.out.println("Numero: " + t.getNumero());
			if (t.getUsuario() != null) {
				System.out.println("-- Usuario --");
				System.out.println("Nome: " + t.getUsuario().getNome());
				System.out.println("Login: " + t.getUsuario().getLogin());
				System.out.println("Senha: " + t.getUsuario().getSenha());
			}
		}
		
	}
	
}
