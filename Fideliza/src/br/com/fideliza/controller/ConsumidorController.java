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
import br.com.fideliza.DAO.RegistraPontosDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.DAO.UtilizaPontosDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.RegistraPontos;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.model.UtilizaPontos;
import br.com.fideliza.util.RecuperaSessao;

public class ConsumidorController {

	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario user = new Usuario();

	private DataModel<Consumidor> consumidorLista;
	private DataModel<Consumidor> listaConsumidorAtivo;
	private DataModel<Consumidor> listaConsumidorDesativado;
	private DataModel<RegistraPontos> listaRegistroConsumidor;
	private DataModel<UtilizaPontos> listaUtilizaPontosFuncionario;

	public ConsumidorController() {

	}

	public String salvaConsumidor() {

		if(verificaEmail(consumidor.getEmail(), consumidor.getEmailConfirm()) == true && verificaCPF(consumidor.getCpf()) == true){	//verifica email e cpf
			
			consumidor.setStatus(true);
			
			user.setConsumidor(consumidor); 
			user.setPassword(consumidor.getPassword());
			user.setUser(consumidor.getCpf());
			user.setPermissaoConsumidor(true);
			
			consumidorDAO.adicionaConsumidor(consumidor);
			usuarioDAO.adicionaUsuario(user); // cria usuario usando o CPF como user
			
			
			return "salvaConsumidor";

		} else {
			return "erroConsumidor";
		}
	}
	
	public boolean verificaEmail(String email, String confirmEmail) { //verifica se email está correto nos dois campos e se já existe algum cadastrado

		if (!email.equals(confirmEmail)) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"email invalido", null));			
			return false;
		}else if(usuarioDAO.buscaPorUser(email) != null){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"email já cadastrado", null));			
			return false;
		} else {
			return true;
		}
	}
	
	public boolean verificaCPF(String cpf) { // verifica se cpf já esta cadastrado

		Consumidor retorno = consumidorDAO.buscaPorCPF(cpf);

		if (retorno != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF já cadastrado", null));
			return false;
			
		} else {
			return true;
		}
	}

	public String editaConsumidor() {

		consumidorDAO.editarConsumidor(consumidor);
		
		return "editadoConsumidor";
		
	}
	
	public String detalhaUsuario(){
		
		return "detalhaUsuario";
	}
	
	//gets e setters
	
	public DataModel<UtilizaPontos> getListaUtilizaPontosFuncionario() { // lista historico de registro de promoções por um funcionario

		if (listaUtilizaPontosFuncionario == null) {
			user = new RecuperaSessao().retornaUsuario();
			consumidor = user.getConsumidor();
			if (listaUtilizaPontosFuncionario == null) {
				List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO().listaUtilizaPontosConsumidor(consumidor);
				listaUtilizaPontosFuncionario = new ListDataModel<UtilizaPontos>(utilizaPontos);
			}
		}
		return listaUtilizaPontosFuncionario;
	}

	public void setListaUtilizaPontosFuncionario(
			DataModel<UtilizaPontos> listaUtilizaPontosFuncionario) {
		this.listaUtilizaPontosFuncionario = listaUtilizaPontosFuncionario;
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

	public DataModel<RegistraPontos> getListaRegistroConsumidor() { //lista registros de um consumidor
		if(listaRegistroConsumidor == null){
			user = new RecuperaSessao().retornaUsuario();
			List<RegistraPontos> registraPontos = new RegistraPontosDAO().listaRegistroConsumidor(user.getConsumidor());
			listaRegistroConsumidor = new ListDataModel<RegistraPontos>(registraPontos);
		}
		return listaRegistroConsumidor;
	}

	public void setListaRegistroConsumidor(
			DataModel<RegistraPontos> listaRegistroConsumidor) {
		this.listaRegistroConsumidor = listaRegistroConsumidor;
	}

	public DataModel<Consumidor> getListaConsumidorDesativado() {
		if(listaConsumidorDesativado == null){
			List<Consumidor> consumidor = new ConsumidorDAO().listaConsumidorDesativado();
			listaConsumidorDesativado = new ListDataModel<Consumidor>(consumidor);
		}
		return listaConsumidorDesativado;
	}

	public void setListaConsumidorDesativado(
			DataModel<Consumidor> listaConsumidorDesativado) {
		this.listaConsumidorDesativado = listaConsumidorDesativado;
	}

	public DataModel<Consumidor> getListaConsumidorAtivo() {
		if(listaConsumidorAtivo == null){
			List<Consumidor> consumidor = new ConsumidorDAO().listaConsumidorAtivo();
			listaConsumidorAtivo = new ListDataModel<Consumidor>(consumidor);
		}
		return listaConsumidorAtivo;
	}

	public void setListaConsumidorAtivo(DataModel<Consumidor> listaConsumidorAtivo) {
		this.listaConsumidorAtivo = listaConsumidorAtivo;
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
