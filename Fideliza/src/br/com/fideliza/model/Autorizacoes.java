package br.com.fideliza.model;

import java.io.Serializable;

public class Autorizacoes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1728166610818059297L;
	
	private int idAutorizacoes;
	private String authority;
	private Usuario usuario;
	
	// getters e setters
	
	public String getAuthority() {
		return authority;
	}
	public int getIdAutorizacoes() {
		return idAutorizacoes;
	}
	public void setIdAutorizacoes(int idAutorizacoes) {
		this.idAutorizacoes = idAutorizacoes;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//hashCode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + idAutorizacoes;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autorizacoes other = (Autorizacoes) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (idAutorizacoes != other.idAutorizacoes)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
