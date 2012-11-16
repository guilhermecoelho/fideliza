package br.com.fideliza.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.com.fideliza.model.Mensagem;
import br.com.fideliza.util.EmailUtil;

public class IndexController {

	private Mensagem mensagem = new Mensagem();

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void enviaEmail() {
		try {
			EmailUtil.enviaEmail(mensagem);
		} catch (EmailException ex) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro! Occoreu um erro ao enviar a mensagem.","Erro"));
		}
	}

	public void limpaCampos() {
		mensagem = new Mensagem();
	}

}