package br.com.fideliza.model;

import java.io.Serializable;

public class UsuarioEmpresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4048399370611445295L;
	
	private int idUsuarioEmpresa;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	private Empresa empresa;
	private TipoUsuario tipoUsuario;
	
	//gets e setters
	
	
	public int getIdUsuarioEmpresa() {
		return idUsuarioEmpresa;
	}
	public void setIdUsuarioEmpresa(int idUsuarioEmpresa) {
		this.idUsuarioEmpresa = idUsuarioEmpresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//hashCode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confirmEmail == null) ? 0 : confirmEmail.hashCode());
		result = prime * result
				+ ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idUsuarioEmpresa;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		UsuarioEmpresa other = (UsuarioEmpresa) obj;
		if (confirmEmail == null) {
			if (other.confirmEmail != null)
				return false;
		} else if (!confirmEmail.equals(other.confirmEmail))
			return false;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idUsuarioEmpresa != other.idUsuarioEmpresa)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tipoUsuario == null) {
			if (other.tipoUsuario != null)
				return false;
		} else if (!tipoUsuario.equals(other.tipoUsuario))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
