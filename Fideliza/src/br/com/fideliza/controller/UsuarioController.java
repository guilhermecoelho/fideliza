package br.com.fideliza.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.ClienteDAO;
import br.com.fideliza.model.Cliente;

public class UsuarioController {
	
	private ClienteDAO clienteDAO;
	private Cliente cliente;
	
	public UsuarioController(){
		this.cliente = new Cliente();
		this.clienteDAO = new ClienteDAO();
	}
	
	public String login(){
		Cliente retorno = clienteDAO.login(cliente.getPassword(),cliente.getCpf());

		if(retorno != null){
			
			cliente = retorno;
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
			session.setAttribute("cliente", cliente); //salva dados do cliente na sessão
			
			if(retorno.getTipoUsuario().getIdTipoUsuario() == 1){
				return "loginOK";
			} else {
				return "loginOK2";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha ou Usuario incorreto", null));
			return "errorLogin";
		}
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
