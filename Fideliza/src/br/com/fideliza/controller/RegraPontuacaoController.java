/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.model.RegraPontuacao;

public class RegraPontuacaoController {

	private RegraPontuacao regraPontuacao;
	private RegraPontuacaoDAO regraPontuacaoDAO;
	private DataModel<RegraPontuacao> listaRegra;

	public RegraPontuacaoController() {
		this.regraPontuacao = new RegraPontuacao();
		this.regraPontuacaoDAO = new RegraPontuacaoDAO();
	}

	public String adicionaRegraPontuacao() {

		if (regraPontuacao != null) {
			
			regraPontuacaoDAO.adicionaRegrapontuacao(regraPontuacao);
			return "salvaRegra";
			
		} else {
			
			return "error";
			
		}
	}

	//getter and setters
	
	public RegraPontuacao getRegraPontuacao() {
		return regraPontuacao;
	}

	public DataModel<RegraPontuacao> getListaRegra() {
		if(regraPontuacao == null){
			List<RegraPontuacao> regraPontuacao = new RegraPontuacaoDAO().listaRegras();
			listaRegra =  new ListDataModel<RegraPontuacao>(regraPontuacao);
		}
		return listaRegra;
	}

	public void setListaRegra(DataModel<RegraPontuacao> listaRegra) {
		this.listaRegra = listaRegra;
	}

	public void setRegraPontuacao(RegraPontuacao regraPontuacao) {
		this.regraPontuacao = regraPontuacao;
	}
}
