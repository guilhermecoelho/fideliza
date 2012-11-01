package br.com.fideliza.controller;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.TabChangeEvent;

import br.com.fideliza.DAO.AdministradorDAO;
import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.model.Administrador;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.RegraPontuacao;

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
	private Consumidor selectedConsumidor;
	private RegraPontuacao selectedRegra;
	private DataModel<Funcionario> listaFuncionarioEmpresa;
	private DataModel<Promocao> listaPromocaoEmpresa;
	private DataModel<Funcionario> listaFuncionariosAtivos;
	private DataModel<Funcionario> listaFuncionariosDesativados;
	private DataModel<Consumidor> listaConsumidorAtivo;
	private DataModel<Consumidor> listaConsumidorDesativado;
	private DataModel<Promocao> listaPromocaoAtiva;
	private DataModel<Promocao> listaPromocaoDesativada;
	private DataModel<RegraPontuacao> listaRegras;
	

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
	
	public void onTabChange(TabChangeEvent event){
		event.getTab();
	}

	//get e set
	
	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public RegraPontuacao getSelectedRegra() {
		return selectedRegra;
	}

	public void setSelectedRegra(RegraPontuacao selectedRegra) {
		this.selectedRegra = selectedRegra;
	}

	public DataModel<RegraPontuacao> getListaRegras() { // lista regras de pontuação
		if(listaRegras == null){
			List<RegraPontuacao> regraPontuacao = new RegraPontuacaoDAO().listaRegras();
			listaRegras = new ListDataModel<RegraPontuacao>(regraPontuacao);
		}
		return listaRegras;
	}

	public void setListaRegras(DataModel<RegraPontuacao> listaRegras) {
		this.listaRegras = listaRegras;
	}

	public DataModel<Promocao> getListaPromocaoAtiva() { // lista promoçoes ativas
		if(listaPromocaoAtiva == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocaoAtiva();
			listaPromocaoAtiva = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoAtiva;
	}

	public void setListaPromocaoAtiva(DataModel<Promocao> listaPromocaoAtiva) {
		this.listaPromocaoAtiva = listaPromocaoAtiva;
	}

	public DataModel<Promocao> getListaPromocaoDesativada() { // lista promocoes desativadas
		if(listaPromocaoDesativada == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocaoDesativada();
			listaPromocaoDesativada = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoDesativada;
	}

	public void setListaPromocaoDesativada(
			DataModel<Promocao> listaPromocaoDesativada) {
		this.listaPromocaoDesativada = listaPromocaoDesativada;
	}

	public Consumidor getSelectedConsumidor() {
		return selectedConsumidor;
	}

	public void setSelectedConsumidor(Consumidor selectedConsumidor) {
		this.selectedConsumidor = selectedConsumidor;
	}

	public DataModel<Consumidor> getListaConsumidorAtivo() { //lista consumidores ativos
		if(listaConsumidorAtivo == null){
			List<Consumidor> consumidor = new ConsumidorDAO().listaConsumidorAtivo();
			listaConsumidorAtivo = new ListDataModel<Consumidor>(consumidor);
		}
		
		return listaConsumidorAtivo;
	}

	public void setListaConsumidorAtivo(DataModel<Consumidor> listaConsumidorAtivo) {
		this.listaConsumidorAtivo = listaConsumidorAtivo;
	}

	public DataModel<Consumidor> getListaConsumidorDesativado() { //lista consumidores desativos
		if(listaConsumidorDesativado == null){
			List<Consumidor> consumidor = new ConsumidorDAO().listaConsumidorDesativado();
			listaConsumidorDesativado = new ListDataModel<Consumidor>(consumidor);
		}
		return listaConsumidorDesativado;
	}

	public void setListaConsumidorDesativado(
			DataModel<Consumidor> listaConsumidorDesativado) {
		this.listaConsumidorDesativado = listaConsumidorDesativado;
	}

	public DataModel<Funcionario> getListaFuncionariosDesativados() { // lista todos funcionarios desativados
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

	public DataModel<Funcionario> getListaFuncionariosAtivos() { // lista todos funcionarios ativos
		if(listaFuncionariosAtivos == null){
			List<Funcionario> funcionario = new FuncionarioDAO().listaFuncionariosAtivos();
			listaFuncionariosAtivos = new ListDataModel<Funcionario>(funcionario);
		}
		return listaFuncionariosAtivos;
	}

	public void setListaFuncionariosAtivos(DataModel<Funcionario> listaFuncionariosAtivos) {
		this.listaFuncionariosAtivos = listaFuncionariosAtivos;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
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
		System.out.println("EMP: "+empresa.getIdEmpresa());
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
