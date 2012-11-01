/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.RegistraPontos;
import br.com.fideliza.util.HibernateUtil;

public class RegistraPontosDAO {
	
	private Session session;
	private Transaction tx;
	
	public RegistraPontosDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
	}
	
	public void SalvaRegistro(RegistraPontos registraPontos){
		
		try{
			session.save(registraPontos);
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
