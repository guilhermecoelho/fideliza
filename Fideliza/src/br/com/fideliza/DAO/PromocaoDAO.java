/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Empresa;
import br.com.fideliza.model.Promocao;
import br.com.fideliza.util.HibernateUtil;

public class PromocaoDAO {
	
	private Session session;
	private Transaction tx;
	
	public PromocaoDAO(){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
	}
	
	public void adicionaPromocao(Promocao promocao){
		try{
			session.save(promocao);
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
	
	public void editaPromocao(Promocao promocao){
		try{
			session.update(promocao);
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
	public List<Promocao> listaPromocao(){
		try{
			return session.createCriteria(Promocao.class).list();
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
	
	
	public Promocao buscaPorEmpresa(int empresa){
		
		try{
			return (Promocao) session.createCriteria(Promocao.class).add(Restrictions.like("empresa.idEmpresa", empresa)).uniqueResult();
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
	public List<Promocao> listaPorPontos(double pontos, Empresa empresa){
		try{
			return session.createCriteria(Promocao.class).add(Restrictions.le("pontos", pontos)).add(Restrictions.like("empresa",empresa)).list();
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
	public Promocao buscaPorId(int idPromocao){
		try{
			return (Promocao) session.createCriteria(Promocao.class).add(Restrictions.like("idPromocao", idPromocao)).uniqueResult();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			session.flush();
			session.clear();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> listaPorEmpresa(Empresa idEmpresa){
		try{
			return session.createCriteria(Promocao.class).add(Restrictions.like("empresa",idEmpresa)).list();
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
	public List<Promocao> listaPromocaoAtivaPorEmpresa(Empresa idEmpresa){
		try{
			return session.createCriteria(Promocao.class).add(Restrictions.like("empresa",idEmpresa)).add(Restrictions.eq("status", true)).list();
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
	public List<Promocao> listaPromocaoAtiva(){
		try{
			return session.createCriteria(Promocao.class).add(Restrictions.eq("status", true)).list();
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
	public List<Promocao> listaPromocaoDesativada(){
		try{
			return session.createCriteria(Promocao.class).add(Restrictions.eq("status", false)).list();
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
