/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import org.hibernate.HibernateException;
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
		try{
			session.save(utilizaPontos);
			tx.commit();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}	
	}
}
