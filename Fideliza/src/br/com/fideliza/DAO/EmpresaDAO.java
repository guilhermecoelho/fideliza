/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	
	@SuppressWarnings("unchecked")
	public List<Empresa> listaEmpresas(){
		return session.createCriteria(Empresa.class).list();
	}
	
	public Empresa BuscaPorCNPJ(String cnpj){

		Empresa retorno;
		String sql= "select empresa from Empresa as empresa where cnpj = :cnpj";
		Query q = session.createQuery(sql);
		q.setString("cnpj", cnpj);
		retorno = (Empresa)  q.uniqueResult();
		
		return retorno;
	}

}
