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
			
			String tipoUser = null;
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
			session.setAttribute("usuario", usuario); //salva dados do usuario na sessão
			
			if(usuario.isPermissaoConsumidor() == true){
				tipoUser = "loginConsumidor";
			}
			else if(usuario.isPermissaoTerminal() == true){
				tipoUser = "loginFuncionario";
			}
			else if(usuario.isPermissaoEmpresa() == true){
				tipoUser =  "loginEmpresa";
			}
			else if(usuario.isPermissaoAdministrador() == true){
				tipoUser = "loginAdmin";
			}
			return tipoUser;

		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha ou Usuario incorreto", null));
			return "errorLogin";
		}
	}
	
	public String logout(){
		return null;
	}
	
	
	//getters and setters

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
