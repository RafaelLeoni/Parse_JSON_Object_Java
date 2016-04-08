package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Usuario {
	
	private String nome;
	private boolean login;
	private String senha;
	
	private Map<String,Object> mapa;

	private Endereco endereco;
	private List<Telefone> telefones;
	
	
	public Usuario() {
	}

	public Usuario(String nome, boolean login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public void addTelefone(Telefone telefone) {
		if (telefones == null) {
			telefones = new ArrayList<>();
		}
		telefones.add(telefone);
	}

	public Map<String,Object> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String,Object> mapa) {
		this.mapa = mapa;
	}
	
}