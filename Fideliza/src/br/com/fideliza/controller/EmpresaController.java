/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.RegraPontuacao;
import br.com.fideliza.model.Usuario;

public class EmpresaController {

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

		if (verificaEmail() == true && verificaCNPJ() == true) {
			
			regraPontuacao = regraPontuacaoDAO.buscaPorId(1);
			empresa.setRegraPontuacao(regraPontuacao);
				
			empresa.setStatus(false);
			empresa.setNovaEmpresa(true);
			empresaDAO.adicionaEmpresa(empresa);

			Empresa retorno = empresaDAO.BuscaPorCNPJ(empresa.getCnpj());

			if (retorno != null) {
				user.setEmpresa(retorno); // seta a FK do campo INT_EMPRESA_USR com o valor do campo INT_ID_EMPRESA_EMP da tabela EMPRESA
				user.setUser(retorno.getEmail());
				user.setPassword(retorno.getPassword());
				user.setPermissaoEmpresa(true);

				usuarioDAO.adicionaUsuario(user); // cria usuario usando o email como user

				return "empresaSalva";
			} else {
				return "error";
			}

		} else {
			return "error";
		}
	}

	public void editaEmpresa() {
		
		empresaDAO.editaEmpresa(empresa);

	}

	public boolean verificaEmail() {

		if (!empresa.getEmail().equals(empresa.getConfirmEmail())) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"email invalido", null));			
			return false;
		}else if(usuarioDAO.buscaPorUser(empresa.getEmail()) != null){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"email já cadastrado", null));			
			return false;
		} else {
			return true;
		}
	}

	public boolean verificaCNPJ() {

		Empresa retorno = empresaDAO.BuscaPorCNPJ(empresa.getCnpj());

		if (retorno != null) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"CNPJ já cadastrado", null));
			return false;
		} else {
			return true;
		}
	}

/*	public String ativarEmpresa (){
		
		empresa = selectedEmpresa;
		empresa.setStatus(true);
		empresa.setNovaEmpresa(false);
		
		empresaDAO.ativaEmpresa(empresa);
		
		return "ativarEmpresa";
	}*/
	
/*	public String detalhaEmpresa(){
		
		empresa = empresaDAO.BuscaPorId(selectedEmpresa.getIdEmpresa());
		
		return "detalhaEmpresa";
		
	}*/
	
	
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
			List<Empresa> empresa = new EmpresaDAO().listaEmpresas();
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
