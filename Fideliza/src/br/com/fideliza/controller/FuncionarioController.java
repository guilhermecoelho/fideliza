/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.RecuperaSessao;
import br.com.fideliza.util.Verificador;

public class FuncionarioController{
	
	public Verificador verificador;
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Funcionario funcionario = new Funcionario();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	
	private DataModel<Funcionario> listaFuncionarioPorEmpresa;
	private DataModel<Funcionario> listaFuncionariosAtivos;
	private DataModel<Funcionario> listaFuncionariosDesativados;
	
	
	public FuncionarioController(){

	}

	public String salvaFuncionario(){
			
		if(verificaEmail(funcionario.getEmail(), funcionario.getEmailConfirm()) == true){
			usuario = new RecuperaSessao().retornaUsuario();
			
			funcionario.setStatus(true);
	
			funcionario.setEmpresa(usuario.getEmpresa());
			
			funcionarioDAO.adicionaFuncionario(funcionario);
			
			usuario.setUser(funcionario.getEmail());
			usuario.setPassword(funcionario.getPassword());
			usuario.setFuncionario(funcionario);
			
			usuario.setPermissaoFuncionario(true);
			usuario.setPermissaoEmpresa(false);
			
			usuarioDAO.adicionaUsuario(usuario);
	
			return "FuncionarioSalva";
		} else {
			return "error";
		}
		
	}
	
	public void editaFuncionario(){
		funcionarioDAO.editaFuncionario(funcionario);
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
	
	//get e setter
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public DataModel<Funcionario> getListaFuncionariosAtivos() { // lista todos funcionarios ativos
		if(listaFuncionariosAtivos == null){
			List<Funcionario> funcionario = new FuncionarioDAO().listaFuncionariosAtivos();
			listaFuncionariosAtivos = new ListDataModel<Funcionario>(funcionario);
		}
		return listaFuncionariosAtivos;
	}

	public void setListaFuncionariosAtivos(
			DataModel<Funcionario> listaFuncionariosAtivos) {
		this.listaFuncionariosAtivos = listaFuncionariosAtivos;
	}

	public DataModel<Funcionario> getListaFuncionariosDesativados() {
		if(listaFuncionariosDesativados == null){
			List<Funcionario> funcionario = new FuncionarioDAO().listaFuncionariosDesativados();
			listaFuncionariosDesativados = new ListDataModel<Funcionario>(funcionario);
		}
		return listaFuncionariosDesativados;
	}

	public void setListaFuncionariosDesativados(
			DataModel<Funcionario> listaFuncionariosDesativados) {
		this.listaFuncionariosDesativados = listaFuncionariosDesativados;
	}

	public DataModel<Funcionario> getListaFuncionarioPorEmpresa() {
		
		if(listaFuncionarioPorEmpresa == null){
			usuario = new RecuperaSessao().retornaUsuario();
			int idEmpresa = usuario.getEmpresa().getIdEmpresa();
			List<Funcionario> funcionario = new FuncionarioDAO().BuscaPorEmpresa(idEmpresa);
			listaFuncionarioPorEmpresa = new ListDataModel<Funcionario> (funcionario);
			
			return listaFuncionarioPorEmpresa;
		}
			
		return listaFuncionarioPorEmpresa;

	}

	public void setListaFuncionarioPorEmpresa(DataModel<Funcionario> listaFuncionarioPorEmpresa) {
		this.listaFuncionarioPorEmpresa = listaFuncionarioPorEmpresa;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}	
}
