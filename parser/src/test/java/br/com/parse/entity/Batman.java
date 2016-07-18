package br.com.parse.entity;

import java.util.Calendar;

public class Batman {

	private String nome;
	private int idade;
	private Long valorInvestimento;
	private String cidade;
	private Calendar dataNascimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getValorInvestimento() {
		return valorInvestimento;
	}
	public void setValorInvestimento(Long valorInvestimento) {
		this.valorInvestimento = valorInvestimento;
	}
}
