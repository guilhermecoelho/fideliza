/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Usuario;

public class EmpresaController {

	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Usuario user;
	private UsuarioDAO usuarioDAO;
	private DataModel<Empresa> empresaLista; // lista empresas
	private ArrayList<Empresa> populaComboBox; // popula comboBox

	public EmpresaController() {
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
		this.user = new Usuario();
		this.usuarioDAO = new UsuarioDAO();
	}

	public String salvaEmpresa() {

		if (verificaEmail() == true && verificaCNPJ() == true) {

			empresa.setStatus(true);
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

	// gets e setters

	public ArrayList<Empresa> getPopulaComboBox() { // popula comboBox
		if (populaComboBox == null) {
			List<Empresa> empresa = new EmpresaDAO().listaEmpresas();
			populaComboBox = new ArrayList<Empresa>(empresa);
		}

		return populaComboBox;
	}

	public void setPopulaComboBox(ArrayList<Empresa> populaComboBox) {
		this.populaComboBox = populaComboBox;
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

}
