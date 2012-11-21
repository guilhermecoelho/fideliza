/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.model;

import java.io.Serializable;
import java.util.Arrays;

public class Promocao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8962113486608891007L;

	private int idPromocao;
	private String nome;
	private String descricao;
	private double desconto;
	private double pontos;
	private Empresa empresa;
	private boolean status = false;
	private boolean enviaEmail = false;
	private byte[] imagem;
	
	// gets e setters
	
	public int getIdPromocao() {
		return idPromocao;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public boolean isEnviaEmail() {
		return enviaEmail;
	}
	public void setEnviaEmail(boolean enviaEmail) {
		this.enviaEmail = enviaEmail;
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
	public double getPontos() {
		return pontos;
	}
	public void setPontos(double pontos) {
		this.pontos = pontos;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	//hashcode
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(desconto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + (enviaEmail ? 1231 : 1237);
		result = prime * result + idPromocao;
		result = prime * result + Arrays.hashCode(imagem);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		temp = Double.doubleToLongBits(pontos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Promocao other = (Promocao) obj;
		if (Double.doubleToLongBits(desconto) != Double
				.doubleToLongBits(other.desconto))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (enviaEmail != other.enviaEmail)
			return false;
		if (idPromocao != other.idPromocao)
			return false;
		if (!Arrays.equals(imagem, other.imagem))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(pontos) != Double
				.doubleToLongBits(other.pontos))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
