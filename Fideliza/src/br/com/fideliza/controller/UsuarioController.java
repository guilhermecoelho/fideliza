/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Usuario;

public class UsuarioController {
	

	private Usuario usuario = new Usuario();
	private Usuario usuarioLogado = new Usuario();
	private UsuarioDAO userDAO = new UsuarioDAO();
	private Consumidor consumidor = new Consumidor();
	
	public UsuarioController(){

	}
	
	public String login(){
		
		try{
			
			Usuario retorno = userDAO.loginCliente(usuario.getPassword(), usuario.getUser());
			
			if(retorno != null){
				usuario = retorno;
				
				String tipoUser = null;
				
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
				session.setAttribute("usuario", usuario); //salva dados do usuario na sessão
				
				if(usuario.isPermissaoConsumidor() == true){
					
					if(usuario.getConsumidor().isStatus() == false){
						tipoUser="desativado";
					}else {
						tipoUser = "loginConsumidor";
						
					}
					
				}
				else if(usuario.isPermissaoFuncionario() == true){
					if(usuario.getFuncionario().isStatus() == false){
						tipoUser="desativado";
					}else {
						tipoUser = "loginFuncionario";
						
					}
				}
				else if(usuario.isPermissaoEmpresa() == true){
					if(usuario.getEmpresa().isStatus() == false){
						tipoUser="desativado";
					}else {
						tipoUser =  "loginEmpresa";
						
					}
				}
				else if(usuario.isPermissaoAdministrador() == true){
					if(usuario.getAdministrador().isStatus() == false){
						tipoUser="desativado";
					}else {
						tipoUser = "loginAdmin";
					}
				}	
				
				this.usuarioLogado = usuario;
				
				this.usuario = new Usuario();
				
				
				return tipoUser;

			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha ou Usuario incorreto", null));
				this.usuario = new Usuario();
				return "errorLogin";
			}
		}catch (HibernateException e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao conectar com o banco de dados", "Erro"));
			return "errorLogin";
		} catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao realizar a tarefa", "Erro"));
			return "errorLogin";
		}
	}
	
	public String logout() throws IOException{
		try{

			this.usuarioLogado = new Usuario();
			userDAO.logout();
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
			session.invalidate(); 
			
			//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
			
			return "logout";
		}catch (HibernateException e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao conectar com o banco de dados", "Erro"));
			return "errorLogout";
		} catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao realizar a tarefa", "Erro"));
			return "errorLogout";
		}
	}
	
	
	public void BuscaPorEmpresa(){
		
	}
	
	//getters and setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
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
