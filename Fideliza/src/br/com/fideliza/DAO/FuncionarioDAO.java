/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Funcionario;
import br.com.fideliza.util.HibernateUtil;

public class FuncionarioDAO {
	
	private Session session;
	private Transaction tx;

	public FuncionarioDAO() {

		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaFuncionario(Funcionario funcionario){
		try{
			session.save(funcionario);
			tx.commit();
			//session.close();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void editaFuncionario(Funcionario funcionario){
		try{
			session.update(funcionario);
			tx.commit();
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listaFuncionarios(){
		try{
			return session.createCriteria(Funcionario.class).list();
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
	public List<Funcionario> listaFuncionariosAtivos(){
		try{
			return session.createCriteria(Funcionario.class).add(Restrictions.eq("status", true)).list();
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
	public List<Funcionario> listaFuncionariosDesativados(){
		try{
			return session.createCriteria(Funcionario.class).add(Restrictions.eq("status", false)).list();
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
	public List<Funcionario> BuscaPorEmpresa(int idEmpresa){
		try{
			return session.createCriteria(Funcionario.class).add(Restrictions.like("empresa.idEmpresa",idEmpresa)).list();
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
	public List<Funcionario> buscaFuncionarioAtivoPorEmpresa(int idEmpresa){
		try{
			return session.createCriteria(Funcionario.class).add(Restrictions.eq("status", true)).add(Restrictions.like("empresa.idEmpresa",idEmpresa)).list();
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
	
	public Funcionario buscaPorId(int idFuncionario){
		try{
			return (Funcionario) session.createCriteria(Funcionario.class).add(Restrictions.eq("idFuncionario", idFuncionario)).uniqueResult();
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
