/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.DAO.UtilizaPontosDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Mensagem;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.model.UtilizaPontos;
import br.com.fideliza.util.EmailUtil;
import br.com.fideliza.util.RecuperaSessao;

public class UtilizaPontosController {

	private Usuario usuario = new Usuario();
	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private Promocao promocao = new Promocao();
	private Promocao selectedPromocao = new Promocao();
	private PromocaoDAO promocaoDAO = new PromocaoDAO();
	private UtilizaPontos utilizaPontos = new UtilizaPontos();
	private UtilizaPontosDAO utilizaPontosDAO = new UtilizaPontosDAO();
	private Mensagem mensagem = new Mensagem();
	
	private DataModel<Promocao> listaPromocao;


	public UtilizaPontosController() {

	}

	public String mostraPromocoes() {

		consumidor = consumidorDAO.buscaPorCPF(utilizaPontos.getCpf());

		if (consumidor != null) {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); //cria uma sessão
			session.setAttribute("cpf", consumidor.getCpf()); //salva dados do consumidor na sessão
			
			return "mostraPromocoes";
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF invalido", null));
			return "errorUtiliza";
		}
	}

	@SuppressWarnings("static-access")
	public void registraUso() throws IOException {
		
		usuario = new RecuperaSessao().retornaUsuario();
		//recupera dados da promoção selecionada
		
		promocao = promocaoDAO.buscaPorId(selectedPromocao.getIdPromocao());
	
			//recupera informações do consumidor salvas na sessão
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			String cpf = (String) session.getAttribute("cpf");
			
			consumidor = consumidorDAO.buscaPorCPF(cpf);
			
			//popula e salva no BD registro utiliza_pontos
			
			utilizaPontos.setConsumidor(consumidor);
			utilizaPontos.setFuncionario(usuario.getFuncionario());
			utilizaPontos.setPromocao(promocao);
			utilizaPontos.setEmpresa(usuario.getFuncionario().getEmpresa());
			utilizaPontos.setDataRegistro(new Date(System.currentTimeMillis()));
			utilizaPontos.setHoraRegistro(new Time(System.currentTimeMillis()));
			
			utilizaPontosDAO.SalvaRegistro(utilizaPontos);
			
			//atualiza saldo consumidor
			
			double pontosConsumidor = consumidor.getPontos();	
			double novoSaldo = pontosConsumidor - promocao.getPontos();	
			consumidor.setPontos(novoSaldo);
			
			consumidorDAO.editarConsumidor(consumidor);
			
			mensagem.setDestino(consumidor.getEmail());
			mensagem.setTitulo("Utilização de Pontos Sistema Fideliza");
			mensagem.setMensagem("Caro sr." +consumidor.getNome()+", o sitema fideliza informa que houve a utilização dos pontos de sua conta . Segue as informações sobre a operação \n\n" +
							"Estabelecimento: "+utilizaPontos.getEmpresa().getNome()+"\n\n"+
							"Promocao :"+utilizaPontos.getPromocao().getNome()+"\n\n"+
							"Quantidade de pontos utilizados: "+utilizaPontos.getPromocao().getPontos()+"\n\n"+
							"Data: " +utilizaPontos.getDataRegistro()+"\n\n"+
							"Hora: "+utilizaPontos.getHoraRegistro()+"\n\n"+
							"Saldo de pontos: "+utilizaPontos.getConsumidor().getPontos());
				
			try{
				new EmailUtil().enviaEmail(mensagem);
			}catch (EmailException ex) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro! Occoreu um erro ao enviar a mensagem.","Erro"));
			}
			
			session.removeAttribute(cpf);
			
			//redireciona para pagina final
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("confirma_utiliza_pontos.xhtml");
			//return "registraUso";

	}

	// getters and setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Promocao getSelectedPromocao() {
		return selectedPromocao;
	}

	public void setSelectedPromocao(Promocao selectedPromocao) {
		this.selectedPromocao = selectedPromocao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public DataModel<Promocao> getListaPromocao() {

		if (listaPromocao == null) {
			usuario = new RecuperaSessao().retornaUsuario();
			List<Promocao> promocao = new PromocaoDAO().listaPorPontos(consumidor.getPontos(), usuario.getEmpresa());
			listaPromocao = new ListDataModel<Promocao>(promocao);
			return listaPromocao;
		}
		return listaPromocao;
	}

	public void setListaPromocao(DataModel<Promocao> listaPromocao) {
		this.listaPromocao = listaPromocao;
	}

	public UtilizaPontos getUtilizaPontos() {
		return utilizaPontos;
	}

	public void setUtilizaPontos(UtilizaPontos utilizaPontos) {
		this.utilizaPontos = utilizaPontos;
	}

}