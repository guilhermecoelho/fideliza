package br.com.fideliza.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.Usuario;

public class RecuperaSessao {
	
	private Empresa empresa = new Empresa();
	private Consumidor consumidor = new Consumidor();
	private Funcionario funcionario = new Funcionario();
	private Promocao promocao = new Promocao();
	private Usuario user = new Usuario();
	
	public Usuario retornaUsuario(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		return user = (Usuario) session.getAttribute("usuario");
	}
	
	public Empresa retornaEmpresa(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		return empresa = (Empresa) session.getAttribute("empresaTeste");

	}
	
	public Consumidor retornaConsumidor(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		return consumidor = (Consumidor) session.getAttribute("consumidor");
	}
	
	public Funcionario retornaFuncionario(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		return funcionario = (Funcionario) session.getAttribute("funcionario");
	}
	
	public Promocao retornaPromocao(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		return promocao = (Promocao) session.getAttribute("promocao");
	}

}
