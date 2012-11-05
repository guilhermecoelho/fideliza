/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.DAO.UsuarioDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Empresa;


public class Verificador {
	
	private ConsumidorDAO consumidorDAO;
	private UsuarioDAO usuarioDAO;
	private EmpresaDAO empresaDAO;
	
	
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
	
	public boolean verificaCPF(String cpf) { // verifica se cpf já esta cadastrado

		Consumidor retorno = consumidorDAO.buscaPorCPF(cpf);

		if (retorno != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"CPF já cadastrado", null));
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

}
