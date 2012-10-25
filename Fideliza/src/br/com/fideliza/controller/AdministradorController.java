package br.com.fideliza.controller;

import br.com.fideliza.DAO.AdministradorDAO;
import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.model.Administrador;
import br.com.fideliza.model.Empresa;

public class AdministradorController {
	
	private Administrador admin;
	private AdministradorDAO adminDAO;
	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Empresa selectedEmpresa;
	
	public AdministradorController(){
		this.admin = new Administrador();
		this.adminDAO = new AdministradorDAO();
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
	}
	
	public String ativarEmpresa (){
		
		empresa = selectedEmpresa;
		empresa.setStatus(true);
		empresa.setNovaEmpresa(false);
		
		empresaDAO.ativaEmpresa(empresa);
		
		return "ativarEmpresa";
	}
	
	public String detalhaEmpresa(){
		
		
		empresa = empresaDAO.BuscaPorId(selectedEmpresa.getIdEmpresa());
		
		
		return "detalhaEmpresa";
		
	}

	//get e set
	
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
	

}
