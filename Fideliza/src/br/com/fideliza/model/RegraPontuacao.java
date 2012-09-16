package br.com.fideliza.model;

import java.io.Serializable;

public class RegraPontuacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5447893810314961779L;
	
	private int idRegraPontuacao;
	private String nome;
	private double valorReal;
	private double valorPonto;
	
	//getters and setters
	
	public int getIdRegraPontuacao() {
		return idRegraPontuacao;
	}
	public void setIdRegraPontuacao(int idRegraPontuacao) {
		this.idRegraPontuacao = idRegraPontuacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValorReal() {
		return valorReal;
	}
	public void setValorReal(double valorReal) {
		this.valorReal = valorReal;
	}
	public double getValorPonto() {
		return valorPonto;
	}
	public void setValorPonto(double valorPonto) {
		this.valorPonto = valorPonto;
	}
	
	//hashCode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRegraPontuacao;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorPonto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorReal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		RegraPontuacao other = (RegraPontuacao) obj;
		if (idRegraPontuacao != other.idRegraPontuacao)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(valorPonto) != Double
				.doubleToLongBits(other.valorPonto))
			return false;
		if (Double.doubleToLongBits(valorReal) != Double
				.doubleToLongBits(other.valorReal))
			return false;
		return true;
	}
}
