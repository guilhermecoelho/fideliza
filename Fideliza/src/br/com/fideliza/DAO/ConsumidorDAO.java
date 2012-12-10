/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Consumidor;
import br.com.fideliza.util.HibernateUtil;

public class ConsumidorDAO {
	
	private Session session;
	private Transaction tx;
	
	public ConsumidorDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaConsumidor (Consumidor consumidor){
		
		tx.begin();
		
		session.save(consumidor);
		
		//session.flush();
		tx.commit();


	}
	
	@SuppressWarnings("unchecked")
	public List<Consumidor> listaConsumidors(){
		try{
			return session.createCriteria(Consumidor.class).list();
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
	
	public void editarConsumidor (Consumidor consumidor){

		try{
			
			session.update(consumidor);
			
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
	public List<Consumidor> listaConsumidorAtivo(){
		try{
			return session.createCriteria(Consumidor.class).add(Restrictions.eq("status", true)).list();
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
	public List<Consumidor> listaConsumidorDesativado(){
		try{
			return session.createCriteria(Consumidor.class).add(Restrictions.eq("status", false)).list();
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
	public List<Consumidor> listaPorPontos(Double pontos){
		try{
			return session.createCriteria(Consumidor.class).add(Restrictions.ge("pontos", pontos)).list();
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
	public List<Consumidor> listaUmConsumidor(int id) throws HibernateException{
			try{
				return session.createCriteria(Consumidor.class).add(Restrictions.like("idConsumidor",id)).list();
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
	
	public Consumidor buscaPorCPF(String cpf){
		
		try{
			return (Consumidor) session.createCriteria(Consumidor.class).add(Restrictions.like("cpf", cpf)).uniqueResult();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			//session.flush();
			//session.clear();
		}
		return null;
	}
	
	public long contaConsumidor(){
		try{
			Criteria criteria = session.createCriteria(Consumidor.class);
			criteria.setProjection(Projections.rowCount());
			long teste = (Long) criteria.uniqueResult();
			
			return teste;
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		return (Long) null;
	}
}
