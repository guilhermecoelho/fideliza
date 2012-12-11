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
import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Funcionario;
import br.com.fideliza.model.Promocao;
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
	
	public UtilizaPontos listaUltimoRegistro(){
		try{
			
			//return session.createCriteria(UtilizaPontos.class).add(Restrictions.("empresa", empresa)).list();
			Criteria criteria = session.createCriteria(UtilizaPontos.class).setProjection(Projections.max("idUtilizaPontos"));
			Integer list = (Integer) criteria.uniqueResult();
			
			session.flush();
			session.clear();
			criteria = null;
			return (UtilizaPontos) session.createCriteria(UtilizaPontos.class).add(Restrictions.eq("idUtilizaPontos", list)).uniqueResult();
            // System.out.println("TESE: "+list);

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
	public List<UtilizaPontos> listaUtilizaPontosFuncionario(Funcionario funcionario){
		try{
			return session.createCriteria(UtilizaPontos.class).add(Restrictions.like("funcionario", funcionario)).list();
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
	public List<UtilizaPontos> listaUtilizaPontosPromocao(Promocao promocao){
		try{
			return session.createCriteria(UtilizaPontos.class).add(Restrictions.like("promocao", promocao)).list();
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
