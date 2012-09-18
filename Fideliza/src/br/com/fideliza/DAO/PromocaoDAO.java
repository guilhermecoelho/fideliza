/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
		session.save(promocao);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> listaPromocao(){
		return session.createCriteria(Promocao.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> listaPromocao2(){
		return session.createQuery("select promocao from Promocao as promocao left join fetch promocao.idEmpresa").list();
	}
	
	public Promocao buscaPorEmpresa(int empresa){

		Promocao retorno;
		String sql= "select promocao from Promocao as promocao where empresa = :empresa";
		Query q = session.createQuery(sql);
		q.setInteger("empresa", empresa);
		retorno = (Promocao)  q.uniqueResult();
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> listaPorPontos(double pontos){
		return session.createCriteria(Promocao.class).add(Restrictions.le("pontos", pontos)).list();
	}

}
