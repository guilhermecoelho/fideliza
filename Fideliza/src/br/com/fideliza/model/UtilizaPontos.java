/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.model;

import java.io.Serializable;
import java.util.Date;

public class UtilizaPontos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5177881612197154089L;
	
	private int idUtilizaPontos;
	private double valorCompra;
	private String cpf;
	private Funcionario funcionario;
	private Consumidor consumidor;
	private Promocao promocao;
	private Date dataRegistro;
	
	//getters and setters
	
	
	public int getIdUtilizaPontos() {
		return idUtilizaPontos;
	}
	public void setIdUtilizaPontos(int idUtilizaPontos) {
		this.idUtilizaPontos = idUtilizaPontos;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Consumidor getConsumidor() {
		return consumidor;
	}
	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}
	public Promocao getPromocao() {
		return promocao;
	}
	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	//hashCode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataRegistro == null) ? 0 : dataRegistro.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + idUtilizaPontos;
		result = prime * result
				+ ((promocao == null) ? 0 : promocao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorCompra);
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
		UtilizaPontos other = (UtilizaPontos) obj;
		if (consumidor == null) {
			if (other.consumidor != null)
				return false;
		} else if (!consumidor.equals(other.consumidor))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataRegistro == null) {
			if (other.dataRegistro != null)
				return false;
		} else if (!dataRegistro.equals(other.dataRegistro))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (idUtilizaPontos != other.idUtilizaPontos)
			return false;
		if (promocao == null) {
			if (other.promocao != null)
				return false;
		} else if (!promocao.equals(other.promocao))
			return false;
		if (Double.doubleToLongBits(valorCompra) != Double
				.doubleToLongBits(other.valorCompra))
			return false;
		return true;
	}
}
