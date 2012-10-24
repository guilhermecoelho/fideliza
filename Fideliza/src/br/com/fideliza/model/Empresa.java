/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.model;

import java.io.Serializable;

public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8732575614776935800L;
	private int idEmpresa;
	private String nome;
	private String cnpj;
	private String email;
	private String ConfirmEmail;
	private String password;
	private RegraPontuacao regraPontuacao;
	private boolean status = false;
	private boolean novaEmpresa = false;

	// gets e setters

	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public boolean isNovaEmpresa() {
		return novaEmpresa;
	}

	public void setNovaEmpresa(boolean novaEmpresa) {
		this.novaEmpresa = novaEmpresa;
	}

	public RegraPontuacao getRegraPontuacao() {
		return regraPontuacao;
	}

	public void setRegraPontuacao(RegraPontuacao regraPontuacao) {
		this.regraPontuacao = regraPontuacao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return ConfirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		ConfirmEmail = confirmEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
				+ ((ConfirmEmail == null) ? 0 : ConfirmEmail.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idEmpresa;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (novaEmpresa ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((regraPontuacao == null) ? 0 : regraPontuacao.hashCode());
		result = prime * result + (status ? 1231 : 1237);
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
		Empresa other = (Empresa) obj;
		if (ConfirmEmail == null) {
			if (other.ConfirmEmail != null)
				return false;
		} else if (!ConfirmEmail.equals(other.ConfirmEmail))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idEmpresa != other.idEmpresa)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (novaEmpresa != other.novaEmpresa)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (regraPontuacao == null) {
			if (other.regraPontuacao != null)
				return false;
		} else if (!regraPontuacao.equals(other.regraPontuacao))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
