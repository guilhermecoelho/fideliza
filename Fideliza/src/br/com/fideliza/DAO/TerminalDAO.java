package br.com.fideliza.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.Terminal;
import br.com.fideliza.util.HibernateUtil;

public class TerminalDAO {
	
	private Session session;
	private Transaction tx;

	public TerminalDAO() {

		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaTerminal(Terminal terminal){
		session.save(terminal);
		tx.commit();
		//session.close();
	}
	
	public Terminal BuscaPorEmpresa(int idEmpresa){

		Terminal retorno;
		String sql= "select terminal from Terminal as terminal where empresa = :idEmpresa";
		Query q = session.createQuery(sql);
		q.setInteger("idEmpresa", idEmpresa);
		retorno = (Terminal)  q.uniqueResult();
		
		return retorno;
	}
}
