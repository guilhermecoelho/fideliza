/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;



import java.sql.Date;
import java.sql.Time;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.RegistraPontosDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.RegistraPontos;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.RecuperaSessao;

public class RegistraPontosController {

	private Usuario usuario = new Usuario();
	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private RegistraPontos registraPontos = new RegistraPontos();
	private RegistraPontosDAO registraPontosDAO = new RegistraPontosDAO();

	public RegistraPontosController() {

	}

	public String registraPonto() {
		usuario = new RecuperaSessao().retornaUsuario();
		
		registraPontos.setFuncionario(usuario.getFuncionario());

		// recupera consumidor

		consumidor = consumidorDAO.buscaPorCPF(registraPontos.getCpf());
		

		if (consumidor != null) {
			if(registraPontos.getValorCompra() <= 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Valor inválido", null));
				return "errorRegistro";
			}else {
					
				registraPontos.setConsumidor(consumidor);
			
				registraPontos.setEmpresa(usuario.getFuncionario().getEmpresa());
				
				registraPontos.setDataRegistro(new Date(System.currentTimeMillis()));
				
				registraPontos.setHoraRegistro(new Time(System.currentTimeMillis()));
				
				
				// recupera regra de pontos
	
				double valorPontos = registraPontos.getFuncionario().getEmpresa().getRegraPontuacao().getValorPonto();
				double valorReal = registraPontos.getFuncionario().getEmpresa().getRegraPontuacao().getValorReal();
	
				// calcula a quantidade de pontos a partir do valor da compra, seguindo a regra de pontos
	
				double pontosTotal = (valorPontos * registraPontos.getValorCompra())/ valorReal;
				registraPontos.setQuantidadePontos(pontosTotal);
	
				// atualiza saldo do consumidor
	
				double saldoAntigo = consumidor.getPontos();
				double saldoNovo = pontosTotal + saldoAntigo;
	
				consumidor.setPontos(saldoNovo);
	
				consumidorDAO.editarConsumidor(consumidor);
				
				//Salva registro
				
				registraPontosDAO.SalvaRegistro(registraPontos);
	
				return "pontosRegistrados";
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF invalido", null));
			return "errorRegistro";
		}
	}
	
	public void relatorioRegistro(){
		
	}
	

	
	public RegistraPontos getRegistraPontos() {
		return registraPontos;
	}

	public void setRegistraPontos(RegistraPontos registraPontos) {
		this.registraPontos = registraPontos;
	}

}
