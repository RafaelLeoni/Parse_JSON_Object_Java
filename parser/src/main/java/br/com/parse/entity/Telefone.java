package br.com.parse.entity;

public class Telefone {
	
	private String numero;
	
	public Telefone() {
	}
	
	@Override
	public String toString() {
		return "{numero: " + numero + "}";
	}

	public Telefone(String numero) {
		super();
		this.setNumero(numero);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
