package main;

import entity.Telefone;
import entity.Usuario;
import exception.ParseException;
import parse.ObjectParse;

public class MainObject {
	
	public static void main(String[] args) {
		
		String json = "{\"usuario\": {\"nome\":\"Master\",\"telefones\":[{\"numero\":\"3213-9452\"},"
				+ "{\"numero\":\"2271-2181\",\"usuario\":{\"nome\":\"Rafael\"\"login\":\"rafael\",\"senha\":\"12345\"}}],"
				+ "\"login\":\"master\",\"endereco\":{\"bairro\":\"Centro\","
				+ "\"rua\":\"Buenos Aires\",\"numero\":\"15\",\"complemento\":\"5º Andar\"},\"senha\":\"teste\"}}";
		
		ObjectParse usuarioParse = new ObjectParse(json);
		try {
			Usuario usuario = (Usuario) usuarioParse.toObject(Usuario.class);
			System.out.println("-- Dados --");
			System.out.println("Nome: " + usuario.getNome());
			System.out.println("Login: " + usuario.getLogin());
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("-- Endereco --");
			System.out.println("Bairro: " + usuario.getEndereco().getBairro());
			System.out.println("Rua: " + usuario.getEndereco().getRua());
			System.out.println("Número: " + usuario.getEndereco().getNumero());
			System.out.println("Complemento: " + usuario.getEndereco().getComplemento());
			for (Telefone t : usuario.getTelefones()) {
				System.out.println("-- Telefone --");
				System.out.println("Numero: " + t.getNumero());
				if (t.getUsuario() != null) {
					System.out.println("-- Usuario --");
					System.out.println("Nome: " + t.getUsuario().getNome());
					System.out.println("Login: " + t.getUsuario().getLogin());
					System.out.println("Senha: " + t.getUsuario().getSenha());
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
