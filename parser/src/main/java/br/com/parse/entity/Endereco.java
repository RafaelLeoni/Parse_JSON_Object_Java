package br.com.parse.entity;

public class Endereco {
	
	private String bairro;
	private Integer numero;
	private Long complemento;
	
	public Endereco() {
	}
	
	public Endereco(String bairro, Integer numero, Long complemento) {
		super();
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "{bairro: " + bairro + ", numero: " + numero + ", complemento: " + complemento + "}";
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getComplemento() {
		return complemento;
	}

	public void setComplemento(Long complemento) {
		this.complemento = complemento;
	}

}
