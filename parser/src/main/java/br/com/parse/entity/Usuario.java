package br.com.parse.entity;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nome;
	private boolean ativo;
	private Integer idade;

	private Endereco endereco;
	private List<Telefone> telefones;
	
	
	public Usuario() {
	}

	public Usuario(String nome, boolean ativo, Integer idade) {
		super();
		this.nome = nome;
		this.ativo = ativo;
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Usuario {nome: " + nome + ", ativo: " + ativo + ", idade: " + idade + ", Endereco: " + endereco + ", Telefones: " + telefones + "}";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
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
			telefones = new ArrayList<Telefone>();
		}
		telefones.add(telefone);
	}
	
}