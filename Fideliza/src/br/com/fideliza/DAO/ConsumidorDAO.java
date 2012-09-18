/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Consumidor;
import br.com.fideliza.util.HibernateUtil;

public class ConsumidorDAO {
	
	private Session session;
	private Transaction tx;
	
	public ConsumidorDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaConsumidor (Consumidor consumidor){
		
		session.save(consumidor);
		tx.commit();
		//session.close();

	}
	
	@SuppressWarnings("unchecked")
	public List<Consumidor> listaConsumidors(){
		return session.createCriteria(Consumidor.class).list();

	}
	
	public void editarConsumidor (Consumidor consumidor){
		session.update(consumidor);
		tx.commit();
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Consumidor> listaUmConsumidor(int id) throws HibernateException{

			return session.createCriteria(Consumidor.class).add(Restrictions.like("idConsumidor",id)).list();

	}
	
	public Consumidor buscaPorCPF(String cpf){

		Consumidor retorno;
		String sql= "select consumidor from Consumidor as consumidor where cpf = :cpf";
		Query q = session.createQuery(sql);
		q.setString("cpf", cpf);
		retorno = (Consumidor)  q.uniqueResult();
		
		return retorno;
	}
}
