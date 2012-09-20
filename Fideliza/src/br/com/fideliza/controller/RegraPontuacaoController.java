/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.model.RegraPontuacao;

public class RegraPontuacaoController {

	private RegraPontuacao regraPontuacao;
	private RegraPontuacaoDAO regraPontuacaoDAO;

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

	public void setRegraPontuacao(RegraPontuacao regraPontuacao) {
		this.regraPontuacao = regraPontuacao;
	}
}