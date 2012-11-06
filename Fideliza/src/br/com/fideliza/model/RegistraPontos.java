/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.model;

/*
 * author: Guilherme Coelho
 */
import java.io.Serializable;
import java.sql.Date;

public class RegistraPontos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7562535553680166994L;
	
	private int idRegistraPontos;
	private double quantidadePontos;
	private double valorCompra;
	private String cpf;
	private Funcionario funcionario;
	private Consumidor consumidor;
	private Empresa empresa;
	private Date dataRegistro;
	
	//getters e setters
	
	public double getQuantidadePontos() {
		return quantidadePontos;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public int getIdRegistraPontos() {
		return idRegistraPontos;
	}
	public void setIdRegistraPontos(int idRegistraPontos) {
		this.idRegistraPontos = idRegistraPontos;
	}
	public void setQuantidadePontos(double quantidadePontos) {
		this.quantidadePontos = quantidadePontos;
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
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	//hashcode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataRegistro == null) ? 0 : dataRegistro.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + idRegistraPontos;
		long temp;
		temp = Double.doubleToLongBits(quantidadePontos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		RegistraPontos other = (RegistraPontos) obj;
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
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (idRegistraPontos != other.idRegistraPontos)
			return false;
		if (Double.doubleToLongBits(quantidadePontos) != Double
				.doubleToLongBits(other.quantidadePontos))
			return false;
		if (Double.doubleToLongBits(valorCompra) != Double
				.doubleToLongBits(other.valorCompra))
			return false;
		return true;
	}
}
