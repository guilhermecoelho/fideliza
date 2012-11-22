/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.hibernate.HibernateException;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.DAO.RegistraPontosDAO;
import br.com.fideliza.DAO.RegraPontuacaoDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.DAO.UtilizaPontosDAO;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.RegistraPontos;
import br.com.fideliza.model.RegraPontuacao;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.model.UtilizaPontos;
import br.com.fideliza.util.RecuperaSessao;
import br.com.fideliza.util.detalhaObjeto;

public class EmpresaController {
	
	private Empresa empresa = new Empresa();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private Empresa selectedEmpresa = new Empresa();
	private RegraPontuacao regraPontuacao = new RegraPontuacao();
	private RegraPontuacaoDAO regraPontuacaoDAO = new RegraPontuacaoDAO();
	private Usuario user = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Funcionario funcionario = new Funcionario();
	private Funcionario selectedFuncionario = new Funcionario();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private Promocao promocao = new Promocao();
	private Promocao selectedPromocao = new Promocao();
	private PromocaoDAO promocaoDAO = new PromocaoDAO();
	private DataModel<Empresa> empresaLista; 
	private DataModel<Empresa> listaEmpresaDesativada;
	private DataModel<Empresa> listaEmpresaNova;
	private DataModel<Funcionario> listaFuncionarioPorEmpresa;
	private DataModel<RegistraPontos> listaRegistrosFuncionario;
	private DataModel<UtilizaPontos> listaHistoricoPromocao;

	public EmpresaController() {
		

	}

	public String salvaEmpresa() {

		
		if(verificaEmail(empresa.getEmail(), empresa.getConfirmEmail()) == true && verificaCNPJ(empresa.getCnpj()) == true){
			
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
			return "erroEmpresa";
		}
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
	
	public boolean verificaCNPJ(String cnpj) { // verifica se CNPJ já está cadastrado

		Empresa retorno = empresaDAO.BuscaPorCNPJ(cnpj);

		if (retorno != null) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"CNPJ já cadastrado", null));
			return false;
		} else {
			return true;
		}
	}

	
	public String detalhaFuncionario(){
		
		funcionario = new detalhaObjeto().detalhaFuncionario(selectedFuncionario.getIdFuncionario());
		
		return "detalhaFuncionario";
		
	}
	public String detalhaFuncionarioDesativado(){
		
		funcionario = new detalhaObjeto().detalhaFuncionarioDesativado(selectedFuncionario.getIdFuncionario());
		
		return "detalhaFuncionarioDesativado";
		
	}
	
	public String detalhaPromocao(){
		
		promocao = new detalhaObjeto().detalhaPromocao(selectedPromocao.getIdPromocao());
		
		return "detalhaPromocao";
		
	}
	
	public void editaEmpresa() {
		
		regraPontuacao = regraPontuacaoDAO.buscaPorId(1);
		empresa.setRegraPontuacao(regraPontuacao);
		
		empresaDAO.editaEmpresa(empresa);
		
	}
	
	public void editaFuncionario(){
		try{
			Funcionario retorno = funcionarioDAO.buscaPorId(funcionario.getIdFuncionario());
			funcionario.setEmpresa(retorno.getEmpresa());
			funcionarioDAO.editaFuncionario(funcionario);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Atualização Completa", "item atualizado com sucesso"));
		} catch (HibernateException e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao conectar com o banco de dados", "Erro"));
		} catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao atualizar", "Erro"));
		}
	}
	
	public void editaPromocao(){
		try{
			Promocao retorno = promocaoDAO.buscaPorId(promocao.getIdPromocao());
			promocao.setEmpresa(retorno.getEmpresa());
			promocaoDAO.editaPromocao(promocao);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Atualização Completa", "item atualizado com sucesso"));
		} catch (HibernateException e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao conectar com o banco de dados", "Erro"));
		} catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao atualizar", "Erro"));
		}
	}
	
	// gets e setters
	
	public DataModel<UtilizaPontos> getListaHistoricoPromocao() {
		if(listaHistoricoPromocao == null){
			promocao = new RecuperaSessao().retornaPromocao();
			List<UtilizaPontos> utilizaPontos = new UtilizaPontosDAO().listaUtilizaPontosPromocao(promocao);
			listaHistoricoPromocao = new ListDataModel<UtilizaPontos>(utilizaPontos);
		}
		return listaHistoricoPromocao;
	}

	public void setListaHistoricoPromocao(
			DataModel<UtilizaPontos> listaHistoricoPromocao) {
		this.listaHistoricoPromocao = listaHistoricoPromocao;
	}
	
	public DataModel<RegistraPontos> getListaRegistrosFuncionario() { // lista todos os registros de pontos de um funcionario
		if(listaRegistrosFuncionario == null){
			funcionario = new RecuperaSessao().retornaFuncionario();
			if(listaRegistrosFuncionario == null){
				List<RegistraPontos> registraPontos = new RegistraPontosDAO().listaRegistroFuncionario(funcionario);
				listaRegistrosFuncionario = new ListDataModel<RegistraPontos>(registraPontos);
			}
		}
		return listaRegistrosFuncionario;
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

	public void setListaRegistrosFuncionario(
			DataModel<RegistraPontos> listaRegistrosFuncionario) {
		this.listaRegistrosFuncionario = listaRegistrosFuncionario;
	}
	
	public Funcionario getSelectedFuncionario() {
		return selectedFuncionario;
	}

	public DataModel<Funcionario> getListaFuncionarioPorEmpresa() { // lista funcionarios de uma empresa
		if(listaFuncionarioPorEmpresa == null){
			user = new RecuperaSessao().retornaUsuario();
			empresa.setIdEmpresa(user.getEmpresa().getIdEmpresa());
			List<Funcionario> funcionario = new FuncionarioDAO().buscaFuncionarioAtivoPorEmpresa(empresa.getIdEmpresa());
			listaFuncionarioPorEmpresa = new ListDataModel<Funcionario>(funcionario);
		}
		return listaFuncionarioPorEmpresa;
	}

	public void setListaFuncionarioPorEmpresa(
			DataModel<Funcionario> listaFuncionarioPorEmpresa) {
		this.listaFuncionarioPorEmpresa = listaFuncionarioPorEmpresa;
	}

	public void setSelectedFuncionario(Funcionario selectedFuncionario) {
		this.selectedFuncionario = selectedFuncionario;
	}


	
	public DataModel<Empresa> getListaEmpresaNova() { // lista empresas novas aguardando ativação
		
		if (listaEmpresaNova == null) {	
			List<Empresa> empresa = new EmpresaDAO().listaEmpresaNova();
			listaEmpresaNova = new ListDataModel<Empresa>(empresa);
		}
		return listaEmpresaNova;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
