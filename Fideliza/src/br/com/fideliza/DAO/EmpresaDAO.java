/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
			session.update(empresa);
			tx.commit();
			session.close();		
	}
	
	public void editaEmpresa(Empresa empresa){
		session.update(empresa);
		tx.commit();
		session.close();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> listaEmpresas(){

		return session.createCriteria(Empresa.class).add(Restrictions.eq("status", true)).add(Restrictions.eq("novaEmpresa", false)).list();
	}
	
	public Empresa BuscaPorCNPJ(String cnpj){

		Empresa retorno;
		String sql= "select empresa from Empresa as empresa where cnpj = :cnpj";
		Query q = session.createQuery(sql);
		q.setString("cnpj", cnpj);
		retorno = (Empresa)  q.uniqueResult();
		
		return retorno;
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
		
		return session.createCriteria(Empresa.class).add(Restrictions.eq("status", false)).add(Restrictions.eq("novaEmpresa", false)).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> listaEmpresaNova(){
		
		return session.createCriteria(Empresa.class).add(Restrictions.eq("status", false)).add(Restrictions.eq("novaEmpresa", true)).list();
	}
}
