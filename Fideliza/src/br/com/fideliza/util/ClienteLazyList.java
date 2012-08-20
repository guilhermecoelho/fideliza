package br.com.fideliza.util;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.fideliza.DAO.ClienteDAO;
import br.com.fideliza.model.Cliente;

public class ClienteLazyList extends LazyDataModel<Cliente>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5871709129781174181L;

	private List<Cliente> listaCliente;
	
	private ClienteDAO clienteDAO;
	
	
	public List<Cliente> load(final int first, final int pageSize,
			final String sortField, final SortOrder sortOrder,
			final Map<String, String> filters) {
		
			listaCliente = clienteDAO.listaClientes();
			
			return listaCliente;
	}

}
