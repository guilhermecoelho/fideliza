package br.com.fideliza.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.Cliente;
import br.com.fideliza.util.HibernateUtil;

public class ClienteDAO {
	
	private Session session;
	private Transaction tx;
	
	public ClienteDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaCliente (Cliente cliente){
		session.save(cliente);
		tx.commit();
		session.close();
	}

}
