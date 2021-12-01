package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Cliente;

@ManagedBean(name="cliente")
@SessionScoped
public class BeanCliente extends Cliente implements Serializable {
	private static final long serialVersionUID = 55556L;
	
	public BeanCliente() {
		iniciaCliente(null);
	}
//Este método es necesario para copiar el piso a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanPisos.
	public void setCliente(Cliente cliente) {
		setId(cliente.getId());
		setLogin(cliente.getLogin());
		setPasswd(cliente.getPasswd());
		setNombre(cliente.getNombre());
		setApellidos(cliente.getApellidos());
		setEmail(cliente.getEmail());
	}
//Iniciamos los datos del piso con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaCliente(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    setId(null);
    	    setLogin(bundle.getString("valorDefectoLogin"));
    	    setPasswd(bundle.getString("valorDefectoPasswd"));
    	    setNombre(bundle.getString("valorDefectoNombre"));
    	    setApellidos(bundle.getString("valorDefectoApellidos"));
    	    setEmail(bundle.getString("valorDefectoEmail"));
	  }	      
}
