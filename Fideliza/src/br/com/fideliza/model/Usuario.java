/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.model;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1326099616426808738L;
	
	private int idUsuario;
	private Consumidor consumidor;
	private Empresa empresa;
	private Funcionario funcionario;
	private Administrador administrador;
	private String password;
	private String user;
	private boolean permissaoAdministrador = false;
	private boolean permissaoConsumidor = false;
	private boolean permissaoEmpresa = false;
	private boolean permissaoFuncionario = false;
	
	// gets e setters
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Consumidor getConsumidor() {
		return consumidor;
	}
	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public boolean isPermissaoAdministrador() {
		return permissaoAdministrador;
	}
	public void setPermissaoAdministrador(boolean permissaoAdministrador) {
		this.permissaoAdministrador = permissaoAdministrador;
	}
	public boolean isPermissaoConsumidor() {
		return permissaoConsumidor;
	}
	public void setPermissaoConsumidor(boolean permissaoConsumidor) {
		this.permissaoConsumidor = permissaoConsumidor;
	}
	public boolean isPermissaoEmpresa() {
		return permissaoEmpresa;
	}
	public void setPermissaoEmpresa(boolean permissaoEmpresa) {
		this.permissaoEmpresa = permissaoEmpresa;
	}
	public boolean isPermissaoFuncionario() {
		return permissaoFuncionario;
	}
	public void setPermissaoFuncionario(boolean permissaoFuncionario) {
		this.permissaoFuncionario = permissaoFuncionario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((administrador == null) ? 0 : administrador.hashCode());
		result = prime * result
				+ ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idUsuario;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + (permissaoAdministrador ? 1231 : 1237);
		result = prime * result + (permissaoConsumidor ? 1231 : 1237);
		result = prime * result + (permissaoEmpresa ? 1231 : 1237);
		result = prime * result + (permissaoFuncionario ? 1231 : 1237);
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	
	//hashCode
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (administrador == null) {
			if (other.administrador != null)
				return false;
		} else if (!administrador.equals(other.administrador))
			return false;
		if (consumidor == null) {
			if (other.consumidor != null)
				return false;
		} else if (!consumidor.equals(other.consumidor))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permissaoAdministrador != other.permissaoAdministrador)
			return false;
		if (permissaoConsumidor != other.permissaoConsumidor)
			return false;
		if (permissaoEmpresa != other.permissaoEmpresa)
			return false;
		if (permissaoFuncionario != other.permissaoFuncionario)
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
