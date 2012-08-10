package br.com.fideliza.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.Usuario;
import br.com.fideliza.util.HibernateUtil;

public class UsuarioDAO {
	
	private Session session;
	private Transaction tx;
	
	public UsuarioDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaUser(Usuario user){
		session.save(user);
		tx.commit();
		session.close();
	}

}
