package br.com.fideliza.model;

import java.io.Serializable;

public class Promocao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8962113486608891007L;

	private int idPromocao;
	private String nome;
	private String descricao;
	private double desconto;
	private double saldoPontos;
	private Empresa idEmpresa;

	// gets e setters

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(int idPromocao) {
		this.idPromocao = idPromocao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getSaldoPontos() {
		return saldoPontos;
	}

	public void setSaldoPontos(double saldoPontos) {
		this.saldoPontos = saldoPontos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// hashCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(desconto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
		result = prime * result + idPromocao;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		temp = Double.doubleToLongBits(saldoPontos);
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
		Promocao other = (Promocao) obj;
		if (Double.doubleToLongBits(desconto) != Double
				.doubleToLongBits(other.desconto))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idEmpresa == null) {
			if (other.idEmpresa != null)
				return false;
		} else if (!idEmpresa.equals(other.idEmpresa))
			return false;
		if (idPromocao != other.idPromocao)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(saldoPontos) != Double
				.doubleToLongBits(other.saldoPontos))
			return false;
		return true;
	}

}
