package br.com.fideliza.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.model.Empresa;

public class EmpresaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7278552676261524272L;
	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Empresa selectEmpresa;
	private DataModel<Empresa> empresaLista;
	
	public EmpresaController(){
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
	}
	
	public String salvaEmpresa(){
		empresaDAO.adicionaEmpresa(empresa);
		return "empresaSalva";
	}
	
	public void editaEmpresa(){
		
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

	public Empresa getSelectEmpresa() {
		return selectEmpresa;
	}

	public void setSelectEmpresa(Empresa selectEmpresa) {
		this.selectEmpresa = selectEmpresa;
	}

	public DataModel<Empresa> getEmpresaLista() { //lista todas as empresas
		if(empresaLista == null){
			List<Empresa> empresa = new EmpresaDAO().listaEmpresas();
			empresaLista = new ListDataModel<Empresa>(empresa);
		}
		return empresaLista;
	}

	public void setEmpresaLista(DataModel<Empresa> empresaLista) {
		this.empresaLista = empresaLista;
	}
	
	
	

}
