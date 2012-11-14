/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.filter;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.fideliza.model.Usuario;

public class AuthorizationListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -157383725113713113L;
	

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		
		boolean isRestric = (currentPage.lastIndexOf("restrito") > - 1); // "true" se pagina acessada está dentro da pasta restrito
		boolean isRestricAdmin = (currentPage.lastIndexOf("restrito/admin") > - 1);
		boolean isRestricConsumidor = (currentPage.lastIndexOf("restrito/consumidor") > - 1);
		boolean isRestricFuncionario = (currentPage.lastIndexOf("restrito/funcionario") > - 1);
		boolean isRestricEmpresa = (currentPage.lastIndexOf("restrito/empresa") > - 1);
		
		if(isRestric ==  true){ // se estiver acessando area restrita, verifica se está logado
			
			boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
			
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			 
			if (!isLoginPage && usuario == null) {
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");
			
			}
		
			// se tentar acessar pagina restrito a admin sem ter permissão
			if(usuario == null || isRestricAdmin == true && usuario.isPermissaoAdministrador() != true){ 
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "loginPage");
			}
			
			// se tentar acessar pagina restrito a consumidor sem ter permissão de consumidor
			if(usuario == null || isRestricConsumidor == true &&  usuario.isPermissaoConsumidor() != true){ 
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "loginPage");
			}
			
			// se tentar acessar pagina restrito a funcionario sem ter permissão de funcionario
			if(usuario == null || isRestricFuncionario == true &&  usuario.isPermissaoFuncionario() != true){ 
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "loginPage");
			}
			
			// se tentar acessar pagina restrito a empresa sem ter permissão de empresa
			if(usuario == null || isRestricEmpresa == true &&  usuario.isPermissaoEmpresa() != true){ 
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "loginPage");
			}	
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
