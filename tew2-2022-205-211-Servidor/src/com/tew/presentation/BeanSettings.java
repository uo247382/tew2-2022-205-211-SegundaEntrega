package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name="settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	// uso de inyecciÃ³n de dependencia
	@ManagedProperty(value = "#{piso}")
	private BeanPiso piso;
	@ManagedProperty(value = "#{cliente")
	private BeanCliente cliente;
	@ManagedProperty(value = "#{PisoParaVisitar}")
	private BeanPisoParaVisitar pisoParaVisitar;
	
	public BeanPiso getPiso() {
		return piso;
	}
	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}
	public BeanCliente getCliente() {
		return cliente;
	}
	public void setCliente(BeanCliente cliente) {
		this.cliente = cliente;
	}
	public BeanPisoParaVisitar getPisoParaVisitar() {
		return pisoParaVisitar;
	}
	public void setPisoParaVisitar(BeanPisoParaVisitar pisoParaVisitar) {
		this.pisoParaVisitar = pisoParaVisitar;
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de cÃ³digo para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida â€œesâ€�
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null) {
				if (piso.getId()==null) //valores por defecto del piso, si no NO inicializar
					piso.iniciaPiso(null);}
			if (cliente != null) {
				if (cliente.getId()==null) //valores por defecto del piso, si no NO inicializar
					cliente.iniciaCliente(null);}
			if (pisoParaVisitar != null) {
				if (pisoParaVisitar.getP()==null) //valores por defecto del piso, si no NO inicializar
					pisoParaVisitar.iniciaPisoParaVisitar(null);}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null) {
				if (piso.getId()==null) //valores por defecto del piso, si no NO inicializar
					piso.iniciaPiso(null);}
			if (cliente != null) {
				if (cliente.getId()==null) //valores por defecto del piso, si no NO inicializar
					cliente.iniciaCliente(null);}
			if (pisoParaVisitar != null) {
				if (pisoParaVisitar.getP()==null) //valores por defecto del piso, si no NO inicializar
					pisoParaVisitar.iniciaPisoParaVisitar(null);}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesiÃ³n)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavÃ­a si
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		// Buscamos el piso en la sesiÃ³n. Esto es un patrÃ³n factorÃ­a
		// claramente.
		piso = (BeanPiso) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("piso"));
		cliente = (BeanCliente) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("cliente"));
		pisoParaVisitar = (BeanPisoParaVisitar) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("pisoParaVisitar"));
		
		// si no existe lo creamos e inicializamos
		if (piso == null) {
			System.out.println("BeanSettings - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("piso", piso);
		}
		if (cliente == null) {
			System.out.println("BeanSettings - No existia");
			cliente = new BeanCliente();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("cliente", cliente);
		}
		if (pisoParaVisitar == null) {
			System.out.println("BeanSettings - No existia");
			pisoParaVisitar = new BeanPisoParaVisitar();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("pisoParaVisitar", pisoParaVisitar);
		}
	}

	// Es sÃ³lo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}
