/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	
	public Funcionario BuscaPorEmpresa(int idEmpresa){

		Funcionario retorno;
		String sql= "select funcionario from Funcionario as funcionario where empresa = :idEmpresa";
		Query q = session.createQuery(sql);
		q.setInteger("idEmpresa", idEmpresa);
		retorno = (Funcionario)  q.uniqueResult();
		
		return retorno;
		
	}
	
	public Funcionario BuscaPorId(int idFuncionario){
		
		Funcionario retorno;
		String sql= "select funcionario from Funcionario as funcionario where idFuncionario = :idFuncionario";
		Query q = session.createQuery(sql);
		q.setInteger("idFuncionario", idFuncionario);
		retorno = (Funcionario)  q.uniqueResult();
		
		return retorno;
	}
}
