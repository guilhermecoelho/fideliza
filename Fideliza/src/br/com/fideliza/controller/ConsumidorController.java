/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.Verificador;

public class ConsumidorController {

	private Consumidor consumidor;
	private ConsumidorDAO consumidorDAO;
	private UsuarioDAO usuarioDAO;
	private Usuario user;
	private Verificador verificador;

	private DataModel<Consumidor> consumidorLista;

	public ConsumidorController() {

		this.consumidor = new Consumidor();
		this.consumidorDAO = new ConsumidorDAO();
		this.usuarioDAO = new UsuarioDAO();
		this.user = new Usuario();
	}

	public String salvaConsumidor() {

		if(verificador.verificaEmail(consumidor.getEmail(), consumidor.getEmailConfirm()) == true && verificador.verificaCPF(consumidor.getCpf()) == true){	//verifica email e cpf
			
			consumidor.setStatus(true);
			
			user.setConsumidor(consumidor); 
			user.setPassword(consumidor.getPassword());
			user.setUser(consumidor.getCpf());
			user.setPermissaoConsumidor(true);

			usuarioDAO.adicionaUsuario(user); // cria usuario usando o CPF como user
			consumidorDAO.adicionaConsumidor(consumidor);
			
			return "save";

		} else {
			return "erro";
		}
	}

	public String editaConsumidor() {

		consumidorDAO.editarConsumidor(consumidor);
		
		return "editadoConsumidor";
		
	}

	public DataModel<Consumidor> getConsumidorLista() { // lista todos consumidors
		if (consumidorLista == null) {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			user = (Usuario) session.getAttribute("usuario"); // recupera dados da sessão

			// método em teste!!!

			if (user != null) { // se estiver logado, lista somente dados do consumidor logado

				int idConsumidor = user.getConsumidor().getIdConsumidor(); // recupera id da sessão
				List<Consumidor> consumidor = new ConsumidorDAO().listaUmConsumidor(idConsumidor);
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
