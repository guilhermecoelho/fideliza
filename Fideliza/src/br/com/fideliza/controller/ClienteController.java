package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.ClienteDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Cliente;
import br.com.fideliza.model.Usuario;

public class ClienteController {

	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private UsuarioDAO usuarioDAO;
	private Usuario user;

	private DataModel<Cliente> clienteLista;

	public ClienteController() {

		this.cliente = new Cliente();
		this.clienteDAO = new ClienteDAO();
		this.usuarioDAO = new UsuarioDAO();
		this.user = new Usuario();
	}

	public String salvaCliente() {

		if (verificaEmail() == true && verificaCPF() == true) { // se o email e CPF estão certos, salva cadastro

			cliente.setStatus(true);

			clienteDAO.adicionaCliente(cliente);

			Cliente retorno = clienteDAO.verificaCPF(cliente.getCpf()); // recupera o cadastro recem-criado no banco de dados, necessario para poder recuperar o ID criado no banco de dados

			if (retorno != null) {

				user.setCliente(retorno); // seta a FK do campo INT_CLIENTE_USR com o valor do campo INT_ID_CLIENTE_CLI da tabela CLIENTE
				user.setPassword(retorno.getPassword());
				user.setUser(retorno.getCpf());
				user.setPermissaoEmpresa(true);

				usuarioDAO.adicionaUsuario(user); // cria usuario usando o CPF como user

				return "save";
				
			} else {
				return "erro";
			}

		} else {
			return "erro";
		}
	}

	public String editaCliente() {

		clienteDAO.editarCliente(cliente);
		return "save";
	}

	public boolean verificaEmail() { // verifica se os campos "email" e "confirma email" são iguais

		if (!cliente.getEmail().equals(cliente.getEmailConfirm())) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"email invalido", null));
			return false;
		} else {
			return true;
		}
	}

	public boolean verificaCPF() { // verifica se cpf já esta cadastrado

		Cliente retorno = clienteDAO.verificaCPF(cliente.getCpf());

		if (retorno != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"CPF já cadastrado", null));
			return false;
		} else {
			return true;
		}
	}

	public DataModel<Cliente> getClienteLista() { // lista todos clientes
		if (clienteLista == null) {

			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);

			user = (Usuario) session.getAttribute("usuario"); // recupera dados da sessão

			// método em teste!!!

			if (user != null) { // se estiver logado, lista somente dados do cliente logado

				int idCliente = user.getCliente().getIdCliente(); // recupera id da sessão
				List<Cliente> cliente = new ClienteDAO()
						.listaUmCliente(idCliente);
				clienteLista = new ListDataModel<Cliente>(cliente);

			} else { // lista todos clientes

				List<Cliente> cliente = new ClienteDAO().listaClientes();
				clienteLista = new ListDataModel<Cliente>(cliente);

			}

		}
		return clienteLista;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setClienteLista(DataModel<Cliente> clienteLista) {
		this.clienteLista = clienteLista;
	}
}
