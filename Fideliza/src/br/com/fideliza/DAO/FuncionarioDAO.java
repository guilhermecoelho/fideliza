/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

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
		session.save(funcionario);
		tx.commit();
		//session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listaFuncionarios(){
		
		return session.createCriteria(Funcionario.class).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> BuscaPorEmpresa(int idEmpresa){
		
		return session.createCriteria(Funcionario.class).add(Restrictions.like("empresa.idEmpresa",idEmpresa)).list();
		
	}
	public Funcionario buscaPorId(int idFuncionario){
		
		return (Funcionario) session.createCriteria(Funcionario.class).add(Restrictions.eq("idFuncionario", idFuncionario)).uniqueResult();
	}
}
