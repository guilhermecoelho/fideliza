/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.RegraPontuacao;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.Verificador;

public class EmpresaController {
	
	private Verificador verificador;
	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Empresa selectedEmpresa;
	private RegraPontuacao regraPontuacao;
	private RegraPontuacaoDAO regraPontuacaoDAO;
	private Usuario user;
	private UsuarioDAO usuarioDAO;
	private DataModel<Empresa> empresaLista; 
	private DataModel<Empresa> listaEmpresaDesativada;
	private DataModel<Empresa> listaEmpresaNova;

	public EmpresaController() {
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
		this.regraPontuacao = new RegraPontuacao();
		this.regraPontuacaoDAO = new RegraPontuacaoDAO();
		this.user = new Usuario();
		this.usuarioDAO = new UsuarioDAO();
	}

	public String salvaEmpresa() {

		
		if(verificador.verificaEmail(empresa.getEmail(), empresa.getConfirmEmail()) == true && verificador.verificaCNPJ(empresa.getCnpj()) == true){
			
			regraPontuacao = regraPontuacaoDAO.buscaPorId(1);
			empresa.setRegraPontuacao(regraPontuacao);
				
			empresa.setStatus(false);
			empresa.setNovaEmpresa(true);
			
			user.setEmpresa(empresa);
			user.setUser(empresa.getEmail());
			user.setPassword(empresa.getPassword());
			user.setPermissaoEmpresa(true);
			
			empresaDAO.adicionaEmpresa(empresa);
			usuarioDAO.adicionaUsuario(user);
			
			return "empresaSalva";
			
		} else {
			return "error";
		}
	}

	public void editaEmpresa() {
		regraPontuacao = regraPontuacaoDAO.buscaPorId(1);
		empresa.setRegraPontuacao(regraPontuacao);
		
		empresaDAO.editaEmpresa(empresa);
		
	}
	
	// gets e setters


	public DataModel<Empresa> getListaEmpresaNova() { // lista empresas novas aguardando ativação
		
		if (listaEmpresaNova == null) {	
			List<Empresa> empresa = new EmpresaDAO().listaEmpresaNova();
			listaEmpresaNova = new ListDataModel<Empresa>(empresa);
		}
		return listaEmpresaNova;
	}


	public void setListaEmpresaNova(DataModel<Empresa> listaEmpresaNova) {
		this.listaEmpresaNova = listaEmpresaNova;
	}

	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(Empresa selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public DataModel<Empresa> getEmpresaLista() { // lista todas as empresas

		if (empresaLista == null) {	
			List<Empresa> empresa = new EmpresaDAO().listaEmpresas2();
			empresaLista = new ListDataModel<Empresa>(empresa);
		}
		return empresaLista;
	}

	public void setEmpresaLista(DataModel<Empresa> empresaLista) {
		this.empresaLista = empresaLista;
	}

	public DataModel<Empresa> getListaEmpresaDesativada() { // lista empresas desativadas
		
		if (listaEmpresaDesativada == null) {	
			List<Empresa> empresa = new EmpresaDAO().listaEmpresaDesativada();
			listaEmpresaDesativada = new ListDataModel<Empresa>(empresa);
		}
		return listaEmpresaDesativada;
	}

	public void setListaEmpresaDesativada(DataModel<Empresa> listaEmpresaDesativada) {
		this.listaEmpresaDesativada = listaEmpresaDesativada;
	}
	
}
