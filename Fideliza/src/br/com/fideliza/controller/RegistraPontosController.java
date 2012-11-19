/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;



import java.sql.Date;
import java.sql.Time;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.RegistraPontosDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Mensagem;
import br.com.fideliza.model.RegistraPontos;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.EmailUtil;
import br.com.fideliza.util.RecuperaSessao;

public class RegistraPontosController {

	private Usuario usuario = new Usuario();
	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private RegistraPontos registraPontos = new RegistraPontos();
	private RegistraPontosDAO registraPontosDAO = new RegistraPontosDAO();
	private Mensagem mensagem = new Mensagem();

	public RegistraPontosController() {

	}

	@SuppressWarnings("static-access")
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
				
				mensagem.setDestino(consumidor.getEmail());
				mensagem.setTitulo("Registro de Pontos Realizado Sistema Fideliza");
				mensagem.setMensagem("Caro sr." +consumidor.getNome()+", o sitema fideliza informa que houve um registro de pontos em sua conta. Segue as informações sobre a operação \n\n" +
								"Estabelecimento: "+registraPontos.getEmpresa().getNome()+"\n\n"+
								"Valor da compra :"+registraPontos.getValorCompra()+"\n\n"+
								"Pontos registrados: "+registraPontos.getQuantidadePontos()+"\n\n"+
								"Data: " +registraPontos.getDataRegistro()+"\n\n"+
								"Hora: "+registraPontos.getHoraRegistro()+"\n\n"+
								"Saldo de pontos: "+registraPontos.getConsumidor().getPontos());
				
				try{
					new EmailUtil().enviaEmail(mensagem);
				}catch (EmailException ex) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro! Occoreu um erro ao enviar a mensagem.","Erro"));
				}
				
	
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
