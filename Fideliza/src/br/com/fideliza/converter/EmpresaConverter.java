package br.com.fideliza.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fideliza.DAO.EmpresaDAO;
import br.com.fideliza.model.Empresa;

@FacesConverter(value="empresaConverter", forClass= Empresa.class)
public class EmpresaConverter implements Converter{
	


	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if(value != null){
			return new EmpresaDAO().BuscaPorId(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if(object != null && object instanceof Empresa) {  
            return ((Empresa)object).getNome().toString();  
        }  
		return null;
	}

}
