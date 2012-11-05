/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.io.IOException;

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
				else if(usuario.isPermissaoFuncionario() == true){
					tipoUser = "loginFuncionario";
				}
				else if(usuario.isPermissaoEmpresa() == true){
					tipoUser =  "loginEmpresa";
				}
				else if(usuario.isPermissaoAdministrador() == true){
					tipoUser = "loginAdmin";
				}
				
				this.usuario = new Usuario();
				
				return tipoUser;

			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha ou Usuario incorreto", null));
				this.usuario = new Usuario();
				return "errorLogin";
			}
		
	}
	
	public String logout() throws IOException{
		
		userDAO.logout();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.invalidate(); 
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		
		return "logout";
	}
	
	
	public void BuscaPorEmpresa(){
		
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
