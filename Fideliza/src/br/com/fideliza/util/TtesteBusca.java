package br.com.fideliza.util;

import br.com.fideliza.DAO.ClienteDAO;
import br.com.fideliza.controller.ClienteController;
import br.com.fideliza.model.Cliente;

public class TtesteBusca {


	
	public static void main(String[] args) {
		
		
		ClienteDAO dao = new ClienteDAO();
		ClienteController co = new ClienteController();
		
			/*for(Cliente c : dao.listaClientes()){
				System.out.println(c.getCpf()+" "+c.getEmail() );
				
			}*/
		
		System.out.println(co.getClienteLista());

	}

}
