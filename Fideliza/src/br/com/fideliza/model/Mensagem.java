package br.com.fideliza.model;

public class Mensagem {
	
	private String destino;
	private String mensagem;
	private String titulo;
	private String contato;
	
	public String getDestino() {
		return destino;
	}
	
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	

}
