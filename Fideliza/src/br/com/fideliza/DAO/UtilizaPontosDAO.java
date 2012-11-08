/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Consumidor;
import br.com.fideliza.model.Empresa;
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
	
	@SuppressWarnings("unchecked")
	public List<UtilizaPontos> listaUtilizaPontosEmpresa(Empresa empresa){
		try{
			return session.createCriteria(UtilizaPontos.class).add(Restrictions.like("empresa", empresa)).list();
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
	
	@SuppressWarnings("unchecked")
	public List<UtilizaPontos> listaUtilizaPontosConsumidor(Consumidor consumidor){
		try{
			return session.createCriteria(UtilizaPontos.class).add(Restrictions.like("consumidor", consumidor)).list();
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
