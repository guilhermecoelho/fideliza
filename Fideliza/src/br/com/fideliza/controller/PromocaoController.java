/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.Usuario;

public class PromocaoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031783898741485634L;
	
	private Usuario usuario;
	private Promocao promocao;
	private PromocaoDAO promocaoDAO;
	private ArrayList<Promocao> promocaoLista;
	
	public PromocaoController(){
		this.usuario = new Usuario();
		this.promocao = new Promocao();
		this.promocaoDAO = new PromocaoDAO();

	}
	
	public String salvaPromocao(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		usuario = (Usuario) session.getAttribute("usuario");
		
		promocao.setEmpresa(usuario.getEmpresa());
		promocao.setStatus(true);
		
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
