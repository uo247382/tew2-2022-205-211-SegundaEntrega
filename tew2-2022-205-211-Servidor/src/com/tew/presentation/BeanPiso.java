package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Piso;

@ManagedBean(name="piso")
@SessionScoped
public class BeanPiso extends Piso implements Serializable {
	private static final long serialVersionUID = 55556L;
	
	public BeanPiso() {
		iniciaPiso(null);
	}
//Este método es necesario para copiar el piso a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanPisos.
	public void setPiso(Piso piso) {
		setId(piso.getId());
		setIDAgente(piso.getIDAgente());
		setPrecio(piso.getPrecio());
		setDireccion(piso.getDireccion());
		setCiudad(piso.getCiudad());
		setAno(piso.getAno());
		setEstado(piso.getEstado());
	}
//Iniciamos los datos del piso con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaPiso(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    setId(null);
    	    setIDAgente(new Long(3));
    	    setPrecio(0.0);
    	    setDireccion(bundle.getString("valorDefectoDireccion"));
    	    setCiudad(bundle.getString("valorDefectoCiudad"));
    	    setAno(0);
    	    setEstado(1);
	  }	      
}
