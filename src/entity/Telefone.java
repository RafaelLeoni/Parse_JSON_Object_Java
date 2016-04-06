package entity;

public class Telefone {
	
	private String numero;
	
	private Usuario usuario;
	
	public Telefone() {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
