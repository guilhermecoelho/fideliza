package br.com.fideliza.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Usuario;

public class UsuarioController {
	

	private Usuario usuario;
	private UsuarioDAO userDAO;
	
	public UsuarioController(){

		this.usuario = new Usuario();
		this.userDAO = new UsuarioDAO();
	}
	
	public String login(){
		
		Usuario retorno = userDAO.loginCliente(usuario.getPassword(), usuario.getUser());
		
		if(retorno != null){
			usuario = retorno;
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
			session.setAttribute("usuario", usuario); //salva dados do usuario na sessão
			
			return "loginOK";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha ou Usuario incorreto", null));
			return "errorLogin";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsuarioDAO userDAO) {
		this.userDAO = userDAO;
	}

}
