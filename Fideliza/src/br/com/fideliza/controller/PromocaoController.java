/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.Usuario;

public class PromocaoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031783898741485634L;
	
	private Usuario usuario;
	private Consumidor consumidor;
	private ConsumidorDAO consumidorDAO;
	private Promocao promocao;
	private PromocaoDAO promocaoDAO;
	private ArrayList<Promocao> promocaoLista;
	private ArrayList<Promocao> listaPromocao;
	private DataModel<Promocao> listaPromocaoAtiva;
	private DataModel<Promocao> listaPromocaoDesativada;
	
	public PromocaoController(){
		this.usuario = new Usuario();
		this.consumidor = new Consumidor();
		this.consumidorDAO = new ConsumidorDAO();
		this.promocao = new Promocao();
		this.promocaoDAO = new PromocaoDAO();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		usuario = (Usuario) session.getAttribute("usuario");

	}
	
	public String salvaPromocao(){
			
		promocao.setEmpresa(usuario.getEmpresa());
		promocao.setStatus(true);
		
		promocaoDAO.adicionaPromocao(promocao);
		return "salvaPromocao";
	}
	
	//gets e setters
	
	public DataModel<Promocao> getListaPromocaoAtiva() { // lista promoçoes ativas
		if(listaPromocaoAtiva == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocaoAtiva();
			listaPromocaoAtiva = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoAtiva;
	}

	public void setListaPromocaoAtiva(DataModel<Promocao> listaPromocaoAtiva) {
		this.listaPromocaoAtiva = listaPromocaoAtiva;
	}

	public DataModel<Promocao> getListaPromocaoDesativada() { // lista promocoes desativadas
		if(listaPromocaoDesativada == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocaoDesativada();
			listaPromocaoDesativada = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoDesativada;
	}

	public void setListaPromocaoDesativada(
			DataModel<Promocao> listaPromocaoDesativada) {
		this.listaPromocaoDesativada = listaPromocaoDesativada;
	}
	
	public ArrayList<Promocao> getPromocaoLista() {
		if(promocaoLista == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocao();
			promocaoLista = new ArrayList<Promocao>(promocao);
		}
		return promocaoLista;
	}

	public ArrayList<Promocao> getListaPromocao() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		String cpf = (String) session.getAttribute("cpf");
		
		consumidor = consumidorDAO.buscaPorCPF(cpf);
			
		if (listaPromocao == null) {
			List<Promocao> promocao = new PromocaoDAO().listaPorPontos(consumidor.getPontos(), usuario.getEmpresa());
			listaPromocao = new ArrayList<Promocao>(promocao);
			return listaPromocao;
		}
		return listaPromocao;
	}

	public void setListaPromocao(ArrayList<Promocao> listaPromocao) {
		this.listaPromocao = listaPromocao;
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
