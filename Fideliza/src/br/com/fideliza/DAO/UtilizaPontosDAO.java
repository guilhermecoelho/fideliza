/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.UtilizaPontos;
import br.com.fideliza.util.HibernateUtil;

public class UtilizaPontosDAO {
	
	private Session session;
	private Transaction tx;
	
	public UtilizaPontosDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
	}
	
	public void SalvaRegistro(UtilizaPontos utilizaPontos ){
		
		session.save(utilizaPontos);
		tx.commit();
		session.close();
		
	}
	

}
