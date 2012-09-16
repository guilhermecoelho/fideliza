/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

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
	
	public void SalvaRegistro(RegistraPontos registraPontos ){
		
		session.save(registraPontos);
		tx.commit();
		session.close();
		
	}

}
