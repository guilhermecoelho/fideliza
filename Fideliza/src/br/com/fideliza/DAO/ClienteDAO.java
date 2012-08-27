package br.com.fideliza.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fideliza.model.Cliente;
import br.com.fideliza.model.TipoUsuario;
import br.com.fideliza.util.HibernateUtil;

public class ClienteDAO {
	
	private Session session;
	private Transaction tx;
	
	public ClienteDAO (){
		
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

	}
	
	public void adicionaCliente (Cliente cliente){
		
		//setar o tipo de usuario
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setIdTipoUsuario(1); //salvar cliente sempre como tipo usuario = 1
		cliente.setTipoUsuario(tipoUsuario);
		
		//salvar no BD
		session.save(cliente);
		tx.commit();
		session.close();

	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listaClientes(){
		return session.createCriteria(Cliente.class).list();

	}
	
	public void editarCliente (Cliente cliente){
		session.update(cliente);
		tx.commit();
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listaUmCliente(int id) throws HibernateException{

			return session.createCriteria(Cliente.class)
					.add(Restrictions.like("idCliente",id)).list();

	}
	

}
