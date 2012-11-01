/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
		try{
			session.save(user);
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
	
	public Usuario loginCliente(String password, String user){
		
	/*	Usuario retorno;
		String sql = "select usuario from Usuario as usuario where password = :password and user = :user";
		Query q = session.createQuery(sql);
		q.setString("user", user);
		q.setString("password", password);
		retorno = (Usuario) q.uniqueResult();
		
		return retorno; */

		try{
			
			return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.like("password",password)).add(Restrictions.like("user",user)).uniqueResult();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void logout(){
		try{
			session.flush();
			session.close();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		
	
	public Usuario buscaPorUser(String user){

	/*	Usuario retorno;
		String sql= "select usuario from Usuario as usuario where user = :user";
		Query q = session.createQuery(sql);
		q.setString("user", user);
		retorno = (Usuario)  q.uniqueResult();
		
		return retorno;*/
		try{
			return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.like("user",user)).uniqueResult();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		return null;
	}
}
