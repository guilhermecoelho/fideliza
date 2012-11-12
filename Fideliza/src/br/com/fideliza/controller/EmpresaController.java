/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.FuncionarioDAO;
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
import br.com.fideliza.util.Verificador;
import br.com.fideliza.util.detalhaObjeto;

public class EmpresaController {
	
	private Verificador verificador;
	private Empresa empresa;
	private EmpresaDAO empresaDAO;
	private Empresa selectedEmpresa;
	private RegraPontuacao regraPontuacao;
	private RegraPontuacaoDAO regraPontuacaoDAO;
	private Usuario user;
	private UsuarioDAO usuarioDAO;
	private Funcionario funcionario;
	private Funcionario selectedFuncionario;
	private Promocao promocao;
	private Promocao selectedPromocao;
	private DataModel<Empresa> empresaLista; 
	private DataModel<Empresa> listaEmpresaDesativada;
	private DataModel<Empresa> listaEmpresaNova;
	private DataModel<Funcionario> listaFuncionarioPorEmpresa;
	private DataModel<RegistraPontos> listaRegistrosFuncionario;
	private DataModel<UtilizaPontos> listaHistoricoPromocao;

	public EmpresaController() {
		this.empresa = new Empresa();
		this.empresaDAO = new EmpresaDAO();
		this.regraPontuacao = new RegraPontuacao();
		this.regraPontuacaoDAO = new RegraPontuacaoDAO();
		this.user = new Usuario();
		this.usuarioDAO = new UsuarioDAO();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		user = (Usuario) session.getAttribute("usuario");
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
