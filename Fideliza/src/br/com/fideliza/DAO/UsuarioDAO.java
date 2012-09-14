/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import org.hibernate.Query;
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
	
	public void adicionaUsuario(Usuario user){
		
		session.save(user);
		tx.commit();
		session.close();
	}
	
	public Usuario loginCliente(String password, String user){
		
		Usuario retorno;
		String sql = "select usuario from Usuario as usuario where password = :password and user = :user";
		Query q = session.createQuery(sql);
		q.setString("user", user);
		q.setString("password", password);
		retorno = (Usuario) q.uniqueResult();
		
		return retorno;
		
	}
	
	public Usuario buscaPorUser(String user){

		Usuario retorno;
		String sql= "select usuario from Usuario as usuario where user = :user";
		Query q = session.createQuery(sql);
		q.setString("user", user);
		retorno = (Usuario)  q.uniqueResult();
		
		return retorno;
	}
}
