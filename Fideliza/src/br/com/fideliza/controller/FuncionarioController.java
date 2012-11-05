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

import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.Verificador;

public class FuncionarioController{
	
	public Verificador verificador;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private Funcionario funcionario;
	private FuncionarioDAO funcionarioDAO;
	
	private DataModel<Funcionario> listaFuncionarioPorEmpresa;
	
	
	public FuncionarioController(){
		this.funcionario = new Funcionario();
		this.funcionarioDAO = new FuncionarioDAO();
		this.usuario = new Usuario();
		this.usuarioDAO = new UsuarioDAO();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		usuario = (Usuario) session.getAttribute("usuario");
	}

	public String salvaFuncionario(){
			
		if(verificaEmail(funcionario.getEmail(), funcionario.getEmailConfirm()) == true){
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
	
	
	public DataModel<Funcionario> getListaFuncionarioPorEmpresa() {
		
		if(listaFuncionarioPorEmpresa == null){
			
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
