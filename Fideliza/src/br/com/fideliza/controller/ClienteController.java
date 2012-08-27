package br.com.fideliza.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.ClienteDAO;
import br.com.fideliza.model.Cliente;

@ManagedBean(name = "ClienteController")
@SessionScoped
public class ClienteController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4157361338947184411L;
	private Cliente cliente;
	private ClienteDAO clienteDAO;

	private Cliente selectCliente;

	private DataModel<Cliente> clienteLista;

	public ClienteController() {

		this.cliente = new Cliente();
		this.clienteDAO = new ClienteDAO();
	}


	public String salvaCliente() {
		if (verificaEmail() == true) { // se o email está certo salva cadastro

			clienteDAO.adicionaCliente(cliente);
			return "save";
		} else {
			return "erro";
		}

	}
	
	public String editaCliente(){
		
		clienteDAO.editarCliente(cliente);
		return "save";
	}

	public boolean verificaEmail() { // verifica se os campos "email" e  "confirma email" são iguais
										
		if (!cliente.getEmail().equals(cliente.getEmailConfirm())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"email invalido", null));
			return false;
		} else {
			return true;
		}
	}

	public DataModel<Cliente> getClienteLista() { // lista todos clientes
		if(clienteLista == null){
			List<Cliente> cliente = new ClienteDAO().listaClientes();
			clienteLista = new ListDataModel<Cliente>(cliente);	
		}
		return clienteLista;

	}

	public Cliente getSelectCliente() {
		return selectCliente;
	}

	public void setSelectCliente(Cliente selectCliente) {
		this.selectCliente = selectCliente;
	}

	public void setClienteLista(DataModel<Cliente> clienteLista) {
		this.clienteLista = clienteLista;
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



}
