/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.DAO.UtilizaPontosDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.model.UtilizaPontos;

public class UtilizaPontosController {

	private Usuario usuario;
	private Consumidor consumidor;
	private ConsumidorDAO consumidorDAO;
	private Promocao promocao;
	private Promocao selectedPromocao;
	private PromocaoDAO promocaoDAO;
	private UtilizaPontos utilizaPontos;
	private UtilizaPontosDAO utilizaPontosDAO;
	private DataModel<Promocao> listaPromocao;
	private ArrayList<Promocao> listaPromocao2;

	public UtilizaPontosController() {

		this.usuario = new Usuario();
		this.consumidor = new Consumidor();
		this.consumidorDAO = new ConsumidorDAO();
		this.promocao = new Promocao();
		this.promocaoDAO = new PromocaoDAO();
		this.utilizaPontos = new UtilizaPontos();
		this.utilizaPontosDAO = new UtilizaPontosDAO();

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");

	}

	public String mostraPromocoes() {

		consumidor = consumidorDAO.buscaPorCPF(utilizaPontos.getCpf());

		if (consumidor != null) {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
			session.setAttribute("consumidor", consumidor); //salva dados do usuario na sessão
			
			return "mostraPromocoes";
		} else {
			return "error";
		}
	}

	public void registraUso() throws IOException {
		
		
		System.out.println(selectedPromocao.getIdPromocao());
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("confirma_utiliza_pontos.xhtml");
		//return "registraUso";

	}

	// getters and setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public ArrayList<Promocao> getListaPromocao2() {
		if (listaPromocao2 == null) {
			List<Promocao> promocao = new PromocaoDAO().listaPorPontos(consumidor.getPontos());
			listaPromocao2 = new ArrayList<Promocao>(promocao);
			return listaPromocao2;
		}
		return listaPromocao2;
	}

	public void setListaPromocao2(ArrayList<Promocao> listaPromocao2) {
		this.listaPromocao2 = listaPromocao2;
	}

	public Promocao getSelectedPromocao() {
		return selectedPromocao;
	}

	public void setSelectedPromocao(Promocao selectedPromocao) {
		this.selectedPromocao = selectedPromocao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public DataModel<Promocao> getListaPromocao() {

		if (listaPromocao == null) {
			List<Promocao> promocao = new PromocaoDAO().listaPorPontos(consumidor.getPontos());
			listaPromocao = new ListDataModel<Promocao>(promocao);
			return listaPromocao;
		}
		return listaPromocao;
	}

	public void setListaPromocao(DataModel<Promocao> listaPromocao) {
		this.listaPromocao = listaPromocao;
	}

	public UtilizaPontos getUtilizaPontos() {
		return utilizaPontos;
	}

	public void setUtilizaPontos(UtilizaPontos utilizaPontos) {
		this.utilizaPontos = utilizaPontos;
	}

}