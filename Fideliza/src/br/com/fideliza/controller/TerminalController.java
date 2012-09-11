package br.com.fideliza.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.TerminalDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Terminal;
import br.com.fideliza.model.Usuario;

public class TerminalController {

	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private Terminal terminal;
	private TerminalDAO terminalDAO;
	
	
	public TerminalController(){
		this.terminal = new Terminal();
		this.terminalDAO = new TerminalDAO();
		this.usuario = new Usuario();
		this.usuarioDAO = new UsuarioDAO();
	}
	

	public String salvaTerminal(){
			
		terminal.setStatus(true);
		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		usuario = (Usuario) session.getAttribute("usuario");

		terminal.setEmpresa(usuario.getEmpresa());
		
		terminalDAO.adicionaTerminal(terminal);
		
		System.out.println(usuario.getEmpresa().getIdEmpresa());
		
		usuario.setUser(terminal.getEmail());
		usuario.setPassword(terminal.getPassword());
		usuario.setTerminal(terminal);
		usuario.setPermissaoTerminal(true);
		usuario.setPermissaoEmpresa(false);
		
		usuarioDAO.adicionaUsuario(usuario);

		return "TerminalSalva";
	}

	//get e setter
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}	
}
