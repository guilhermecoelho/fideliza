package br.com.fideliza.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.PieChartModel;

import br.com.fideliza.DAO.AdministradorDAO;
import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.DAO.RegistraPontosDAO;
import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.DAO.UtilizaPontosDAO;
import br.com.fideliza.model.Administrador;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.RegistraPontos;
import br.com.fideliza.model.RegraPontuacao;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.model.UtilizaPontos;
import br.com.fideliza.util.Verificador;

public class AdministradorController {
	
	private Verificador verificador;
	private Administrador admin;
	private AdministradorDAO adminDAO;
	private Usuario user;
	private UsuarioDAO usuarioDAO;
	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Empresa selectedEmpresa;
	private FuncionarioDAO funcionarioDAO;
	private Funcionario funcionario;
	private Funcionario selectedFuncionario;
	private Promocao promocao;
	private Promocao selectedPromocao;
	private PromocaoDAO promocaoDAO;
	private Consumidor consumidor;
	private Consumidor selectedConsumidor;
	private ConsumidorDAO consumidorDAO;
	private RegraPontuacao selectedRegra;
	private RegistraPontos registraPontos;
	private DataModel<Funcionario> listaFuncionarioEmpresa;
	private DataModel<Promocao> listaPromocaoEmpresa;
	private DataModel<Funcionario> listaFuncionariosAtivos;
	private DataModel<Funcionario> listaFuncionariosDesativados;
	private DataModel<Consumidor> listaConsumidorAtivo;
	private DataModel<Consumidor> listaConsumidorDesativado;
	private DataModel<Promocao> listaPromocaoAtiva;
	private DataModel<Promocao> listaPromocaoDesativada;
	private DataModel<RegraPontuacao> listaRegras;
	private DataModel<RegistraPontos> listaRegistrosEmpresa;
	private DataModel<UtilizaPontos> listaUtilizaPontosEmpresa;
	

	public AdministradorController(){
		this.admin = new Administrador();
		this.adminDAO = new AdministradorDAO();
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
		this.funcionarioDAO = new FuncionarioDAO();
		this.promocaoDAO = new PromocaoDAO();
		this.user = new Usuario();
		this.usuarioDAO = new UsuarioDAO();
		this.verificador = new Verificador();
		this.consumidorDAO = new ConsumidorDAO();
	}
	
	public String salvaAdmin(){
		

		if(verificaEmail(admin.getEmail(), admin.getConfirmEmail()) == true){
			
			admin.setStatus(true);
			
			user.setAdministrador(admin);
			user.setUser(admin.getEmail());
			user.setPassword(admin.getPassword());
			user.setPermissaoAdministrador(true);
			
			adminDAO.salvaAdmin(admin);	
			usuarioDAO.adicionaUsuario(user);	
			
			
			return "salvaAdmin";
			
		} else {
			return "errorAdmin";
		}
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
	
	public String editaConsumidor(){
		
		//consumidorDAO.editarConsumidor(consumidor);
		
		return "editaConsumidor";
	}

	

	//get e set
	
	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public DataModel<UtilizaPontos> getListaUtilizaPontosEmpresa() { //lista histórico de promoções utilizadas por empresa
		if(listaUtilizaPontosEmpresa == null){
			if (listaUtilizaPontosEmpresa == null){
				List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO().listaUtilizaPontosEmpresa(selectedEmpresa);
				listaUtilizaPontosEmpresa = new ListDataModel<UtilizaPontos>(utilizaPontos);
			}
		}
		return listaUtilizaPontosEmpresa;
	}

	public void setListaUtilizaPontosEmpresa(
			DataModel<UtilizaPontos> listaUtilizaPontosEmpresa) {
		this.listaUtilizaPontosEmpresa = listaUtilizaPontosEmpresa;
	}

	public RegistraPontos getRegistraPontos() {
		return registraPontos;
	}

	public void setRegistraPontos(RegistraPontos registraPontos) {
		this.registraPontos = registraPontos;
	}

	public DataModel<RegistraPontos> getListaRegistrosEmpresa() { // lista histórico de registro de pontos
		if (listaRegistrosEmpresa == null){
			List<RegistraPontos> registraPontos = new RegistraPontosDAO().listaRegistroEmpresa(selectedEmpresa);
			listaRegistrosEmpresa = new ListDataModel<RegistraPontos>(registraPontos);
		}
		return listaRegistrosEmpresa;
	}

	public void setListaRegistrosEmpresa(
			DataModel<RegistraPontos> listaRegistrosEmpresa) {
		this.listaRegistrosEmpresa = listaRegistrosEmpresa;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
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
