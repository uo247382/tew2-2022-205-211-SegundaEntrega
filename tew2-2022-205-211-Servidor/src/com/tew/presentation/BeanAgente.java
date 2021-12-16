package com.tew.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tew.model.Agente;

@ManagedBean(name="agente")
@SessionScoped
public class BeanAgente extends Agente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6266108879037402513L;
	
	public BeanAgente() {
		iniciaAgente(null);
	}
	
	public void iniciaAgente(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = 
	 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		this.setId(null);
		this.setLogin(bundle.getString("loginAgenteDefecto"));
		this.setPasswd(bundle.getString("passwdAgenteDefecto"));
	}

	public void setAgente(Agente agente) {
		this.setId(agente.getId());
		this.setLogin(agente.getLogin());
		this.setPasswd(agente.getPasswd());
	}
}

