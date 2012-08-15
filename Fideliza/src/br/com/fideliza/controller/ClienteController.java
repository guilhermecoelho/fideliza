package br.com.fideliza.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fideliza.DAO.ClienteDAO;
import br.com.fideliza.model.Cliente;


@ManagedBean(name = "ClienteController")
@RequestScoped
public class ClienteController {

	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private boolean flag = false;

	public ClienteController() {
		
		this.cliente = new Cliente();
		this.clienteDAO = new ClienteDAO();
	}

	public String salvaCliente() {
		if(verificaEmail() == true){
			flag = true;
			clienteDAO.adicionaCliente(cliente);
			return "save";
		} else {
			return "erro";
		}
		
	}
	
	public boolean verificaEmail(){
		if(!cliente.getEmail().equals(cliente.getEmailConfirm())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "email invalido", null));
			return false;
		} else {
			return true;
		}
	}

	public Cliente getcliente() {
		return cliente;
	}

	public void setcliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDAO getclienteDAO() {
		return clienteDAO;
	}

	public void setclienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
