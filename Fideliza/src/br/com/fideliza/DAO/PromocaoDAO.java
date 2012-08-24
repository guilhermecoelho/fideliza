package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.Promocao;
import br.com.fideliza.util.HibernateUtil;

public class PromocaoDAO {
	
	private Session session;
	private Transaction tx;
	
	public PromocaoDAO(){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
	}
	
	public void adicionaPromocao(Promocao promocao){
		session.save(promocao);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> listaPromocao(){
		return session.createCriteria(Promocao.class).list();
	}

}
