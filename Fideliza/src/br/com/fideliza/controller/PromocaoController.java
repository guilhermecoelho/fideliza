/*
 * author: Guilherme Coelho
 */

package br.com.fideliza.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;

import br.com.fideliza.DAO.ConsumidorDAO;
import br.com.fideliza.DAO.PromocaoDAO;
import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Mensagem;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.EmailUtil;
import br.com.fideliza.util.RecuperaSessao;

public class PromocaoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031783898741485634L;
	
	private Mensagem mensagem = new Mensagem();
	private Usuario usuario = new Usuario();
	private Consumidor consumidor = new Consumidor();
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private Promocao promocao = new Promocao();
	private PromocaoDAO promocaoDAO = new PromocaoDAO();
	
	private ArrayList<Promocao> promocaoLista;
	private ArrayList<Promocao> listaPromocao;
	private DataModel<Promocao> listaPromocaoAtiva;
	private DataModel<Promocao> listaPromocaoDesativada;
	private DataModel<Promocao> listaPromocaoAtivaPorEmpresa;
	private ArrayList<Consumidor> listaConsumidor;

	
	public PromocaoController(){

	}
	public void fileUploadAction(FileUploadEvent event) {
        	 byte[] img = event.getFile().getContents();
        	 String imagem = event.getFile().getFileName();
        	 FacesContext facesContext = FacesContext.getCurrentInstance();
        	 ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        	 String arquivo = scontext.getRealPath("/images/" +imagem);
        	 criaArquivo(img, arquivo);
        	 
    }
	
	public void criaArquivo(byte [] bytes, String arquivo){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException ex) {
	      ex.printStackTrace();  
	     }	
	}
	@SuppressWarnings("static-access")
	public String salvaPromocao(){
		
		try{
			if(promocao.getPontos() <=0 || promocao.getDesconto() <=0 || promocao.getDesconto() > 100){ //verifica se os valores de ponto e desconto são válidos
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Valor inválido", null));
				return "errorPromocao";
			} else {
				usuario = new RecuperaSessao().retornaUsuario();	
				
				promocao.setEmpresa(usuario.getEmpresa());
				promocao.setStatus(true);
				
				promocaoDAO.adicionaPromocao(promocao);
				
				if(promocao.isEnviaEmail() == true){ // se a opção "enviar email" for marcada, manda um email sobra a promoção para todos os consumidores com pontos suficientes para usar ela
					if(listaConsumidor == null){
						List<Consumidor> consumidor = new ConsumidorDAO().listaPorPontos(promocao.getPontos());
						listaConsumidor = new ArrayList<Consumidor>(consumidor);
					}
					for(int i=0; i < listaConsumidor.size();i++){
						
						consumidor = listaConsumidor.get(i);
						
						mensagem.setDestino(consumidor.getEmail());
						mensagem.setTitulo("Uma nova promoção para você!");
						mensagem.setMensagem("Caro sr." +consumidor.getNome()+", o sitema fideliza informa que houve a utilização dos pontos de sua conta . Segue as informações sobre a operação \n\n" +
								"Estabelecimento: "+promocao.getEmpresa().getNome()+"\n\n"+
								"Promocao :"+promocao.getNome()+"\n\n"+
								"Quantidade de pontos utilizados: "+promocao.getPontos()+"\n\n");
						try{
							new EmailUtil().enviaEmail(mensagem);
						}catch (EmailException ex) {
							FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro! Occoreu um erro ao enviar a mensagem.","Erro"));
						}
					}
				}
				return "salvaPromocao";
			}
		}catch (HibernateException e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao conectar com o banco de dados", "Erro"));
			return "errorPromocao";
		} catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro ao realizar a tarefa", "Erro"));
			return "errorPromocao";
		}
	}
	
	public void editaPromocao(){
		
		promocaoDAO.editaPromocao(promocao);
	}
	//gets e setters
	
	public DataModel<Promocao> getListaPromocaoAtiva() { // lista promoçoes ativas
		if(listaPromocaoAtiva == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocaoAtiva();
			listaPromocaoAtiva = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoAtiva;
	}



	public DataModel<Promocao> getListaPromocaoAtivaPorEmpresa() { // lista promoções ativas de uma empresa
		if(listaPromocaoAtivaPorEmpresa == null){
			usuario = new RecuperaSessao().retornaUsuario();
			List<Promocao> promocao = new PromocaoDAO().listaPromocaoAtivaPorEmpresa(usuario.getEmpresa());
			listaPromocaoAtivaPorEmpresa = new ListDataModel<Promocao>(promocao);
		}
		return listaPromocaoAtivaPorEmpresa;
	}

	public void setListaPromocaoAtivaPorEmpresa(
			DataModel<Promocao> listaPromocaoAtivaPorEmpresa) {
		this.listaPromocaoAtivaPorEmpresa = listaPromocaoAtivaPorEmpresa;
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
	
	public ArrayList<Promocao> getPromocaoLista() {
		if(promocaoLista == null){
			List<Promocao> promocao = new PromocaoDAO().listaPromocao();
			promocaoLista = new ArrayList<Promocao>(promocao);
		}
		return promocaoLista;
	}

	public ArrayList<Promocao> getListaPromocao() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		String cpf = (String) session.getAttribute("cpf");
		
		consumidor = consumidorDAO.buscaPorCPF(cpf);
			
		if (listaPromocao == null) {
			usuario = new RecuperaSessao().retornaUsuario();
			List<Promocao> promocao = new PromocaoDAO().listaPorPontos(consumidor.getPontos(), usuario.getEmpresa());
			listaPromocao = new ArrayList<Promocao>(promocao);
			return listaPromocao;
		}
		return listaPromocao;
	}

	public void setListaPromocao(ArrayList<Promocao> listaPromocao) {
		this.listaPromocao = listaPromocao;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public PromocaoDAO getPromocaoDAO() {
		return promocaoDAO;
	}

	public void setPromocaoDAO(PromocaoDAO promocaoDAO) {
		this.promocaoDAO = promocaoDAO;
	}

	public void setPromocaoLista(ArrayList<Promocao> promocaoLista) {
		this.promocaoLista = promocaoLista;
	}
	

	
	

}
