/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Empresa;
import br.com.fideliza.util.HibernateUtil;

public class EmpresaDAO {

	private Session session;
	private Transaction tx;

	public EmpresaDAO() {

		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaEmpresa(Empresa empresa){
			session.save(empresa);
			tx.commit();
			//session.close();
	}
	
	public void ativaEmpresa(Empresa empresa){
			
		try{
			session.update(empresa);
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
	
	public void editaEmpresa(Empresa empresa){
		try{
			session.update(empresa);
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
	public List<Empresa> listaEmpresas(){
		try{
			return session.createCriteria(Empresa.class).add(Restrictions.eq("status", true)).add(Restrictions.eq("novaEmpresa", false)).list();
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
	
	public long contaEmpresa(){
		try{
			Criteria criteria = session.createCriteria(Empresa.class);
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
	@SuppressWarnings("unchecked")
	public List<Empresa> listaEmpresas2(){
		try{
			return session.createCriteria(Empresa.class).list();
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
	
	public Empresa BuscaPorCNPJ(String cnpj){

		/*Empresa retorno;
		String sql= "select empresa from Empresa as empresa where cnpj = :cnpj";
		Query q = session.createQuery(sql);
		q.setString("cnpj", cnpj);
		retorno = (Empresa)  q.uniqueResult();
		
		return retorno;*/
		return (Empresa) session.createCriteria(Empresa.class).add(Restrictions.like("cnpj", cnpj)).uniqueResult();
	}
	

	public Empresa BuscaPorId(int idEmpresa){

		Empresa retorno;
		String sql= "select empresa from Empresa as empresa where idEmpresa = :idEmpresa";
		Query q = session.createQuery(sql);
		q.setInteger("idEmpresa", idEmpresa);
		retorno = (Empresa)  q.uniqueResult();
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> listaEmpresaDesativada(){
		try{
			return session.createCriteria(Empresa.class).add(Restrictions.eq("status", false)).add(Restrictions.eq("novaEmpresa", false)).list();
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
	public List<Empresa> listaEmpresaNova(){
		try{
			return session.createCriteria(Empresa.class).add(Restrictions.eq("status", false)).add(Restrictions.eq("novaEmpresa", true)).list();
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
