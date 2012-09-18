/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
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
	private PromocaoDAO promocaoDAO;
	private UtilizaPontos utilizaPontos;
	private UtilizaPontosDAO utilizaPontosDAO;
	private DataModel<Promocao> listaPromocao;
	
	public UtilizaPontosController(){
		
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
	
	public String registraUso(){
		
		
			return "mostraPromocoes";
			

		
		
	}
	//getters and setters

	public Usuario getUsuario() {
		return usuario;
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
		
		
		utilizaPontos.setFuncionario(usuario.getFuncionario());
		
		//recupera consumidor
		
		//consumidor = consumidorDAO.buscaPorCPF(utilizaPontos.getCpf());
		consumidor = consumidorDAO.buscaPorCPF("999.999.999-99");
		
		if(consumidor != null){

			double pontos = consumidor.getPontos();
				
			if(listaPromocao == null){
				List<Promocao> promocao = new PromocaoDAO().listaPorPontos(pontos);
				listaPromocao = new ListDataModel<Promocao>(promocao);
				return listaPromocao;
			}
			return listaPromocao;
		}
		return listaPromocao;
	}

	public void setListaPromocao(DataModel<Promocao> listaPromocao) {
		this.listaPromocao = listaPromocao;
	}
}


