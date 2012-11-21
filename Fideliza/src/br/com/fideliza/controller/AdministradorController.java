package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
import br.com.fideliza.util.RecuperaSessao;
import br.com.fideliza.util.detalhaObjeto;

public class AdministradorController {

	private Administrador admin = new Administrador();
	private Administrador selectedAdmin = new Administrador();
	private AdministradorDAO adminDAO = new AdministradorDAO();
	
	private Usuario user = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	private Empresa empresa = new Empresa();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private Empresa selectedEmpresa = new Empresa();

	private Funcionario funcionario = new Funcionario();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private Funcionario selectedFuncionario = new Funcionario();
	
	private Promocao promocao = new Promocao();
	private Promocao selectedPromocao = new Promocao();
	private PromocaoDAO promocaoDAO = new PromocaoDAO();

	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private Consumidor selectedConsumidor = new Consumidor();

	private RegraPontuacao selectedRegra = new RegraPontuacao();
	private RegraPontuacao regraPontuacao = new RegraPontuacao();
	private RegistraPontos registraPontos = new RegistraPontos();
	
	private DataModel<Administrador> listaAdminAtivo;
	private DataModel<Funcionario> listaFuncionarioEmpresa;
	private DataModel<Promocao> listaPromocaoEmpresa;
	private DataModel<RegraPontuacao> listaRegras;
	private DataModel<RegistraPontos> listaRegistrosEmpresa;
	private DataModel<UtilizaPontos> listaUtilizaPontosEmpresa;
	private DataModel<RegistraPontos> listaRegistrosConsumidor;
	private DataModel<UtilizaPontos> listaUtilizaPontosConsumidor;
	private DataModel<RegistraPontos> listaRegistrosFuncionario;
	private DataModel<UtilizaPontos> listaUtilizaPontosFuncionario;
	private DataModel<UtilizaPontos> listaUtilizaPontosPromocao;

	public AdministradorController() {

	}

	public String salvaAdmin() {

		if (verificaEmail(admin.getEmail(), admin.getConfirmEmail()) == true) {

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

	public void editaAdmin() {

		adminDAO.editaAdmin(admin);
	}

	public String ativarEmpresa() {

		empresa = selectedEmpresa;
		empresa.setStatus(true);
		empresa.setNovaEmpresa(false);

		empresaDAO.ativaEmpresa(empresa);

		return "ativarEmpresa";
	}
	
	public String detalhaAdmin(){
		
		admin = new detalhaObjeto().detalhaAdministrador(selectedAdmin.getIdAdministrador());
		
		return "detalhaAdmin";
	}

	public String detalhaEmpresa() {

		empresa = new detalhaObjeto().detalhaEmpresa(selectedEmpresa.getIdEmpresa());

		return "detalhaEmpresa";

	}

	public String detalhaEmpresaDesativada() {

		empresa = new detalhaObjeto().detalhaEmpresaDesativada(selectedEmpresa.getIdEmpresa());

		return "detalhaEmpresaDesativada";

	}

	public String detalhaFuncionario() {

		funcionario = new detalhaObjeto().detalhaFuncionario(selectedFuncionario.getIdFuncionario());

		return "detalhaFuncionario";

	}

	public String detalhaFuncionarioDesativado() {

		funcionario = new detalhaObjeto().detalhaFuncionarioDesativado(selectedFuncionario.getIdFuncionario());

		return "detalhaFuncionarioDesativado";

	}

	public String detalhaPromocao() {

		promocao = new detalhaObjeto().detalhaPromocao(selectedPromocao.getIdPromocao());

		return "detalhaPromocao";

	}

	public String detalhaPromocaoDesativada() {

		promocao = new detalhaObjeto().detalhaPromocaoDesativada(selectedPromocao.getIdPromocao());

		return "detalhaPromocaoDesativada";

	}

	public String detalhaConsumidor() {

		consumidor = new detalhaObjeto().detalhaConsumidor(selectedConsumidor.getCpf());

		return "detalhaConsumidor";
	}

	public String detalhaConsumidorDesativado() {

		consumidor = new detalhaObjeto().detalhaConsumidorDesativado(selectedConsumidor.getCpf());

		return "detalhaConsumidorDesativado";
	}

	public void editaEmpresa() {
		regraPontuacao = new RegraPontuacaoDAO().buscaPorId(1);
		empresa.setRegraPontuacao(regraPontuacao);
		
		empresaDAO.editaEmpresa(empresa);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("atualizado com sucesso"));

	}
	
	public void editaConsumidor() {
		
		consumidorDAO.editarConsumidor(consumidor);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("atualizado com sucesso"));
		
	}
	
	public void editaFuncionario(){
		
		Funcionario retorno = funcionarioDAO.buscaPorId(funcionario.getIdFuncionario());
		funcionario.setEmpresa(retorno.getEmpresa());
		funcionarioDAO.editaFuncionario(funcionario);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("atualizado com sucesso"));
	}
	
	public void editaPromocao(){
		
		Promocao retorno = promocaoDAO.buscaPorId(promocao.getIdPromocao());
		promocao.setEmpresa(retorno.getEmpresa());
		promocaoDAO.editaPromocao(promocao);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("atualizado com sucesso"));
		
	}

	public boolean verificaEmail(String email, String confirmEmail) { // verifica se email está correto nosdois campos e se já existe algum cadastrado

		if (!email.equals(confirmEmail)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"email invalido", null));
			return false;
		} else if (usuarioDAO.buscaPorUser(email) != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"email já cadastrado", null));
			return false;
		} else {
			return true;
		}
	}

	//get e set
	
	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public Administrador getSelectedAdmin() {
		return selectedAdmin;
	}

	public void setSelectedAdmin(Administrador selectedAdmin) {
		this.selectedAdmin = selectedAdmin;
	}

	public DataModel<Administrador> getListaAdminAtivo() { //lista administradores ativos
		if(listaAdminAtivo == null){
			List<Administrador> administrador = adminDAO.listaAdmin();
			listaAdminAtivo = new ListDataModel<Administrador>(administrador);
		}
		
		return listaAdminAtivo;
	}

	public void setListaAdminAtivo(DataModel<Administrador> listaAdminAtivo) {
		this.listaAdminAtivo = listaAdminAtivo;
	}

	public DataModel<UtilizaPontos> getListaUtilizaPontosPromocao() { // lista histórico de uso de uma promoção

		if (listaUtilizaPontosPromocao == null) {
			promocao = new RecuperaSessao().retornaPromocao();
			List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO()
					.listaUtilizaPontosPromocao(promocao);
			listaUtilizaPontosPromocao = new ListDataModel<UtilizaPontos>(
					utilizaPontos);
		}
		return listaUtilizaPontosPromocao;
	}

	public void setListaUtilizaPontosPromocao(
			DataModel<UtilizaPontos> listaUtilizaPontosPromocao) {
		this.listaUtilizaPontosPromocao = listaUtilizaPontosPromocao;
	}

	public DataModel<UtilizaPontos> getListaUtilizaPontosFuncionario() { // lista historico de registro de promoções por um funcionario

		if (listaUtilizaPontosFuncionario == null) {

			funcionario = new RecuperaSessao().retornaFuncionario();
			if (listaUtilizaPontosFuncionario == null) {
				List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO()
						.listaUtilizaPontosFuncionario(funcionario);
				listaUtilizaPontosFuncionario = new ListDataModel<UtilizaPontos>(
						utilizaPontos);
			}
		}
		return listaUtilizaPontosFuncionario;
	}

	public void setListaUtilizaPontosFuncionario(
			DataModel<UtilizaPontos> listaUtilizaPontosFuncionario) {
		this.listaUtilizaPontosFuncionario = listaUtilizaPontosFuncionario;
	}

	public DataModel<RegistraPontos> getListaRegistrosFuncionario() { // lista todos os registro de pontos de um funcinario

		if (listaRegistrosFuncionario == null) {
			// funcionario = recuperaSessaoFuncionario();
			funcionario = new RecuperaSessao().retornaFuncionario();
			if (listaRegistrosFuncionario == null) {
				List<RegistraPontos> registraPontos = new RegistraPontosDAO()
						.listaRegistroFuncionario(funcionario);
				listaRegistrosFuncionario = new ListDataModel<RegistraPontos>(
						registraPontos);
			}
		}
		return listaRegistrosFuncionario;
	}

	public void setListaRegistrosFuncionario(
			DataModel<RegistraPontos> listaRegistrosFuncionario) {
		this.listaRegistrosFuncionario = listaRegistrosFuncionario;
	}

	public DataModel<UtilizaPontos> getListaUtilizaPontosConsumidor() { // lista histórico de promoções utilizadas por um consumidor

		if (listaUtilizaPontosConsumidor == null) {

			consumidor = new RecuperaSessao().retornaConsumidor();
			if (listaUtilizaPontosConsumidor == null) {
				List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO()
						.listaUtilizaPontosConsumidor(consumidor);
				listaUtilizaPontosConsumidor = new ListDataModel<UtilizaPontos>(
						utilizaPontos);
			}
		}
		return listaUtilizaPontosConsumidor;
	}

	public void setListaUtilizaPontosConsumidor(
			DataModel<UtilizaPontos> listaUtilizaPontosConsumidor) {
		this.listaUtilizaPontosConsumidor = listaUtilizaPontosConsumidor;
	}

	public DataModel<RegistraPontos> getListaRegistrosConsumidor() { // lista histórico de pontos registrados de um consumidor
		
		if (listaRegistrosConsumidor == null) {
			// consumidor = recuperaSessaoConsumidor();
			consumidor = new RecuperaSessao().retornaConsumidor();
			if (listaRegistrosConsumidor == null) {
				List<RegistraPontos> registraPontos = new RegistraPontosDAO()
						.listaRegistroConsumidor(consumidor);
				listaRegistrosConsumidor = new ListDataModel<RegistraPontos>(
						registraPontos);
			}
		}
		return listaRegistrosConsumidor;
	}

	public void setListaRegistrosConsumidor(
			DataModel<RegistraPontos> listaRegistrosConsumidor) {
		this.listaRegistrosConsumidor = listaRegistrosConsumidor;
	}

	public DataModel<UtilizaPontos> getListaUtilizaPontosEmpresa() { // lista histórico de promoções utilizadas por empresa

		if (listaUtilizaPontosEmpresa == null) {
			empresa = new RecuperaSessao().retornaEmpresa();
			if (listaUtilizaPontosEmpresa == null) {
				List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO()
						.listaUtilizaPontosEmpresa(empresa);
				listaUtilizaPontosEmpresa = new ListDataModel<UtilizaPontos>(
						utilizaPontos);
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
		
		if (listaRegistrosEmpresa == null) {

			empresa = new RecuperaSessao().retornaEmpresa();
			List<RegistraPontos> registraPontos = new RegistraPontosDAO()
					.listaRegistroEmpresa(empresa);
			listaRegistrosEmpresa = new ListDataModel<RegistraPontos>(
					registraPontos);
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

		if (listaRegras == null) {
			List<RegraPontuacao> regraPontuacao = new RegraPontuacaoDAO()
					.listaRegras();
			listaRegras = new ListDataModel<RegraPontuacao>(regraPontuacao);
		}
		return listaRegras;
	}

	public void setListaRegras(DataModel<RegraPontuacao> listaRegras) {
		this.listaRegras = listaRegras;
	}

	public Consumidor getSelectedConsumidor() {
		return selectedConsumidor;
	}

	public void setSelectedConsumidor(Consumidor selectedConsumidor) {
		this.selectedConsumidor = selectedConsumidor;
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

		if (listaPromocaoEmpresa == null) {
			empresa = new RecuperaSessao().retornaEmpresa();
			List<Promocao> promocao = new PromocaoDAO()
					.listaPorEmpresa(empresa);
			listaPromocaoEmpresa = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoEmpresa;
	}

	public void setListaPromocaoEmpresa(DataModel<Promocao> listaPromocaoEmpresa) {
		this.listaPromocaoEmpresa = listaPromocaoEmpresa;
	}

	public DataModel<Funcionario> getListaFuncionarioEmpresa() { // lista funcionarios de uma empresa

		if (listaFuncionarioEmpresa == null) {

			empresa = new RecuperaSessao().retornaEmpresa();
			List<Funcionario> funcionario = new FuncionarioDAO()
					.BuscaPorEmpresa(empresa.getIdEmpresa());
			listaFuncionarioEmpresa = new ListDataModel<Funcionario>(
					funcionario);
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
