package br.com.fideliza.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fideliza.DAO.AdministradorDAO;
import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.FuncionarioDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.model.Administrador;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;

public class detalhaObjeto {
	
	private Empresa empresa = new Empresa();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private Funcionario funcionario = new Funcionario();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private Promocao promocao = new Promocao();
	private PromocaoDAO promocaoDAO = new PromocaoDAO();
	private Administrador admin = new Administrador();
	private AdministradorDAO adminDAO = new AdministradorDAO();
	
	public Administrador detalhaAdministrador(int idAdmin){
		
		admin = adminDAO.buscaPorId(idAdmin);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("administrador", admin); //salva dados do usuario na sessão
		
		return admin;
		
	}
	
	public Empresa detalhaEmpresa(int idEmpresa){
		
		empresa = empresaDAO.BuscaPorId(idEmpresa);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("empresaTeste", empresa); //salva dados do usuario na sessão
		
		return empresa;
		
		
	}
	public Empresa detalhaEmpresaDesativada(int idEmpresa){
		
		empresa = empresaDAO.BuscaPorId(idEmpresa);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("empresaTeste", empresa); //salva dados do usuario na sessão
		
		return empresa;
		
	}
	
	public Funcionario detalhaFuncionario(int idFuncionario){
		
		funcionario = funcionarioDAO.buscaPorId(idFuncionario);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("funcionario", funcionario); //salva dados do usuario na sessão
		
		return funcionario;
		
	}
	public Funcionario detalhaFuncionarioDesativado(int idFuncionario){
		
		funcionario = funcionarioDAO.buscaPorId(idFuncionario);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("funcionario", funcionario); //salva dados do usuario na sessão
		
		return funcionario;
		
	}
	
	public Promocao detalhaPromocao(int idPromocao){
		
		promocao = promocaoDAO.buscaPorId(idPromocao);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("promocao", promocao); //salva dados do usuario na sessão
		
		return promocao;
		
	}
	public Promocao detalhaPromocaoDesativada(int idPromocao){
		
		promocao = promocaoDAO.buscaPorId(idPromocao);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("promocao", promocao); //salva dados do usuario na sessão
		
		return promocao;
		
	}
	
	public Consumidor detalhaConsumidor(String cpf){
		
		consumidor = consumidorDAO.buscaPorCPF(cpf);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("consumidor", consumidor); //salva dados do usuario na sessão
		
		return consumidor;
	}
	public Consumidor detalhaConsumidorDesativado(String cpf){
		
		consumidor = consumidorDAO.buscaPorCPF(cpf);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
		session.setAttribute("consumidor", consumidor); //salva dados do usuario na sessão
		
		return consumidor;
	}

}
