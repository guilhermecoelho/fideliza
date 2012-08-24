package br.com.fideliza.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.model.Promocao;

public class PromocaoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031783898741485634L;
	private Promocao promocao;
	private PromocaoDAO promocaoDAO;
	private ArrayList<Promocao> promocaoLista;
	
	public PromocaoController(){
		this.promocao = new Promocao();
		this.promocaoDAO = new PromocaoDAO();

	}
	
	public String salvaPromocao(){
		promocaoDAO.adicionaPromocao(promocao);
		return "save";
	}
	
	//gets e setters

	public ArrayList<Promocao> getPromocaoLista() {
		if(promocaoLista == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocao();
			promocaoLista = new ArrayList<Promocao>(promocao);
		}
		return promocaoLista;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public PromocaoDAO getPromocaoDAO() {
		return promocaoDAO;
	}

	public void setPromocaoDAO(PromocaoDAO promocaoDAO) {
		this.promocaoDAO = promocaoDAO;
	}

	public void setPromocaoLista(ArrayList<Promocao> promocaoLista) {
		this.promocaoLista = promocaoLista;
	}
	

	
	

}
