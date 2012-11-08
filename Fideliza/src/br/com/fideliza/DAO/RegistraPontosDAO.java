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
	
	@SuppressWarnings("unchecked")
	public List<RegistraPontos> listaRegistroFuncionario(int funcionario){
		try{
			return session.createCriteria(RegistraPontos.class).add(Restrictions.eq("funcionario", funcionario)).list();
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
	public List<RegistraPontos> listaRegistroEmpresa(Empresa empresa){
		try{
			return session.createCriteria(RegistraPontos.class).add(Restrictions.like("empresa", empresa)).list();
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
	public List<RegistraPontos> listaRegistroConsumidor(Consumidor consumidor){
		try{
			return session.createCriteria(RegistraPontos.class).add(Restrictions.like("consumidor", consumidor)).list();
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
