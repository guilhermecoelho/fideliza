package br.com.fideliza.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.fideliza.model.Mensagem;

public class EmailUtil {
	
	private static String HOSTEMAIL = "smtp.gmail.com";
	private static String USERNAME = "fidelizaweb";
	private static String PASSWORD = "fideliza01*";
	private static String EMAILORIGEM = "fidelizaweb@gmail.com";
	
	public static Email conectaEmail() throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName(HOSTEMAIL);
		email.setSmtpPort(587);
		email.setAuthenticator( new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setTLS(true);
		email.setFrom(EMAILORIGEM);
		return email;
	}
	
	public static void enviaEmail(Mensagem mensagem) throws EmailException{
		Email email = new SimpleEmail();
		email = conectaEmail();
		email.setSubject(mensagem.getTitulo());
		email.setMsg(mensagem.getMensagem());
		email.addTo(mensagem.getDestino());
		email.send();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " + mensagem.getDestino(), "Informação"));	
	}

}
