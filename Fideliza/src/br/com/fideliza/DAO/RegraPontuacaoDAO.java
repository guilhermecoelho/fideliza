package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fideliza.model.RegraPontuacao;
import br.com.fideliza.util.HibernateUtil;

public class RegraPontuacaoDAO {

	private Session session;
	private Transaction tx;

	public RegraPontuacaoDAO() {

		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}

	public void adicionaRegrapontuacao(RegraPontuacao regraPontuacao) {

		session.save(regraPontuacao);
		tx.commit();
		session.close();

	}
	
	public RegraPontuacao buscaPorId(int idRegraPontuacao){

		RegraPontuacao retorno;
		String sql= "select regraPontuacao from RegraPontuacao as regraPontuacao where idRegraPontuacao = :idRegraPontuacao";
		Query q = session.createQuery(sql);
		q.setInteger("idRegraPontuacao", idRegraPontuacao);
		retorno = (RegraPontuacao)  q.uniqueResult();
		
		return retorno;
	}
	

	@SuppressWarnings("unchecked")
	public List<RegraPontuacao> listaRegras(){
		
		try{
			
			return session.createCriteria(RegraPontuacao.class).list();
			
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
