/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Usuario;

public class ConsumidorController {

	private Consumidor consumidor;
	private ConsumidorDAO consumidorDAO;
	private UsuarioDAO usuarioDAO;
	private Usuario user;

	private DataModel<Consumidor> consumidorLista;

	public ConsumidorController() {

		this.consumidor = new Consumidor();
		this.consumidorDAO = new ConsumidorDAO();
		this.usuarioDAO = new UsuarioDAO();
		this.user = new Usuario();
	}

	public String salvaConsumidor() {

		if (verificaEmail() == true && verificaCPF() == true) { // se o email e CPF estão certos, salva cadastro

			consumidor.setStatus(true);
			
			consumidorDAO.adicionaConsumidor(consumidor);

			Consumidor retorno = consumidorDAO.buscaPorCPF(consumidor.getCpf()); // recupera o cadastro recem-criado no banco de dados, necessario para poder recuperar o ID criado no banco de dados
			
			if (retorno != null) {

				user.setConsumidor(retorno); // seta a FK do campo INT_CLIENTE_USR com o valor do campo INT_ID_CLIENTE_CLI da tabela CLIENTE
				user.setPassword(retorno.getPassword());
				user.setUser(retorno.getCpf());
				user.setPermissaoConsumidor(true);

				usuarioDAO.adicionaUsuario(user); // cria usuario usando o CPF como user

				return "save";
				
			} else {
				return "erro";
			}

		} else {
			return "erro";
		}
	}

	public String editaConsumidor() {

		consumidorDAO.editarConsumidor(consumidor);
		return "save";
	}

	public boolean verificaEmail() { // verifica se os campos "email" e "confirma email" são iguais

		if (!consumidor.getEmail().equals(consumidor.getEmailConfirm())) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"email invalido", null));
			return false;
		} else {
			return true;
		}
	}

	public boolean verificaCPF() { // verifica se cpf já esta cadastrado

		Consumidor retorno = consumidorDAO.buscaPorCPF(consumidor.getCpf());

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

	public DataModel<Consumidor> getConsumidorLista() { // lista todos consumidors
		if (consumidorLista == null) {

			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);

			user = (Usuario) session.getAttribute("usuario"); // recupera dados da sessão

			// método em teste!!!

			if (user != null) { // se estiver logado, lista somente dados do consumidor logado

				int idConsumidor = user.getConsumidor().getIdConsumidor(); // recupera id da sessão
				List<Consumidor> consumidor = new ConsumidorDAO()
						.listaUmConsumidor(idConsumidor);
				consumidorLista = new ListDataModel<Consumidor>(consumidor);

			} else { // lista todos consumidores

				List<Consumidor> consumidor = new ConsumidorDAO().listaConsumidors();
				consumidorLista = new ListDataModel<Consumidor>(consumidor);

			}

		}
		return consumidorLista;

	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public void setConsumidorLista(DataModel<Consumidor> consumidorLista) {
		this.consumidorLista = consumidorLista;
	}
}
