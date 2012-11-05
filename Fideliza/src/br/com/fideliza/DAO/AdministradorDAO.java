package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Administrador;
import br.com.fideliza.util.HibernateUtil;

public class AdministradorDAO {
	
	private Session session;
	private Transaction tx;
	
	public AdministradorDAO(){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
	}
	
	public void salvaAdmin(Administrador admin){
		
		tx.begin();
		
		session.save(admin);
		
		session.flush();
		tx.commit();

	}
	
	@SuppressWarnings("unchecked")
	public List<Administrador> listaAdmin(){
		try{
			return session.createCriteria(Administrador.class).list();
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
	
	public Administrador buscaPorId(int idAdmin){
		
		try{
			return (Administrador) session.createCriteria(Administrador.class).add(Restrictions.eq("idAdministrador",idAdmin)).uniqueResult();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			session.flush();
			//session.close();
		}
		return null;
	}
}
