package br.com.fideliza.controller;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.fideliza.DAO.AdministradorDAO;
import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.model.Administrador;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;

public class AdministradorController {
	
	private Administrador admin;
	private AdministradorDAO adminDAO;
	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Empresa selectedEmpresa;
	private FuncionarioDAO funcionarioDAO;
	private Funcionario funcionario;
	private Funcionario selectedFuncionario;
	private Promocao promocao;
	private Promocao selectedPromocao;
	private PromocaoDAO promocaoDAO;
	private DataModel<Funcionario> listaFuncionarioEmpresa;
	private DataModel<Promocao> listaPromocaoEmpresa;
	
	public AdministradorController(){
		this.admin = new Administrador();
		this.adminDAO = new AdministradorDAO();
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
		this.funcionarioDAO = new FuncionarioDAO();
		this.promocaoDAO = new PromocaoDAO();
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
	public void detalhaFuncionario(){
		
		funcionario = funcionarioDAO.buscaPorId(selectedFuncionario.getIdFuncionario());
		
	}
	
	public void detalhaPromocao(){
		
		promocao = promocaoDAO.buscaPorId(selectedPromocao.getIdPromocao());
		
	}
	
	public void editaEmpresa() {
		
		empresaDAO.editaEmpresa(empresa);

	}

	//get e set
	
	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public Promocao getSelectedPromocao() {
		return selectedPromocao;
	}

	public void setSelectedPromocao(Promocao selectedPromocao) {
		this.selectedPromocao = selectedPromocao;
	}

	public Funcionario getSelectedFuncionario() {
		return selectedFuncionario;
	}

	public void setSelectedFuncionario(Funcionario selectedFuncionario) {
		this.selectedFuncionario = selectedFuncionario;
	}

	public DataModel<Promocao> getListaPromocaoEmpresa() { // lista promoções de uma empresa
		if(listaPromocaoEmpresa == null){
			List<Promocao> promocao = new PromocaoDAO().listaPorEmpresa(selectedEmpresa);
			listaPromocaoEmpresa = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoEmpresa;
	}

	public void setListaPromocaoEmpresa(DataModel<Promocao> listaPromocaoEmpresa) {
		this.listaPromocaoEmpresa = listaPromocaoEmpresa;
	}

	public DataModel<Funcionario> getListaFuncionarioEmpresa() { //lista funcionarios de uma empresa
		
		if(listaFuncionarioEmpresa == null){
			List<Funcionario> funcionario = new FuncionarioDAO().BuscaPorEmpresa(selectedEmpresa.getIdEmpresa());
			listaFuncionarioEmpresa = new ListDataModel<Funcionario>(funcionario);
		}
		return listaFuncionarioEmpresa;
	}

	public void setListaFuncionarioEmpresa(
			DataModel<Funcionario> listaFuncionarioEmpresa) {
		this.listaFuncionarioEmpresa = listaFuncionarioEmpresa;
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
