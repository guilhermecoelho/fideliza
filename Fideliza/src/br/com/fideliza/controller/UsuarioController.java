package br.com.fideliza.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Usuario;


@ManagedBean(name = "UsuarioController")
@RequestScoped
public class UsuarioController {

	private Usuario user;
	private UsuarioDAO userDAO;
	private boolean flag = false;

	public UsuarioController() {
		this.user = new Usuario();
		this.userDAO = new UsuarioDAO();
	}

	public String salvaUser() {
		flag = true;
		userDAO.adicionaUser(user);
		return "login";
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public UsuarioDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsuarioDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
