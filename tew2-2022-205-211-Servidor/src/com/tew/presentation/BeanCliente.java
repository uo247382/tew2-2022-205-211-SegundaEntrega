package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Cliente;
import com.tew.model.Piso;

@ManagedBean(name="cliente")
@SessionScoped
public class BeanCliente extends Cliente implements Serializable {
	private static final long serialVersionUID = 55556L;
	
	public BeanCliente() {
		iniciaCliente(null);
	}
//Este método es necesario para copiar el Piso a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanPisos.
	public void setCliente(Cliente c) {
		setId(c.getId());
		setLogin(c.getLogin());
		setPasswd(c.getPasswd());
		setNombre(c.getNombre());
		setApellidos(c.getApellidos());
		setEmail(c.getEmail());
	}
//Iniciamos los datos del Cliente con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaCliente(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    setId(null);
    	    setLogin(bundle.getString("loginClienteDefecto"));
    	    setPasswd(bundle.getString("passwdClienteDefecto"));
    	    setNombre(bundle.getString("nombreClienteDefecto"));
    	    setApellidos(bundle.getString("apellidosClienteDefecto"));
    	    setEmail(bundle.getString("emailClienteDefecto"));
	  }	      
}
