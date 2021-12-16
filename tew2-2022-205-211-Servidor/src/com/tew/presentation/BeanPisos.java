package com.tew.presentation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;

@ManagedBean
@SessionScoped
public class BeanPisos implements Serializable{
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el Piso concreto selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.



	private Piso[] pisos = null;
	private String ciudad = null;

	//uso de inyección de dependencia
	@ManagedProperty(value="#{piso}") 
	private BeanPiso piso;
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public BeanPiso getPiso() { return piso; }
	public void setPiso(BeanPiso piso) {this.piso = piso;}

	
	/* public BeanPisos()
          {
        	  iniciaPiso(null);
          }*/

	public Piso[] getPisos () {
		return(pisos);
	}

	public void setPisos(Piso[] pisos) {
		this.pisos = pisos;
	}  
	public void iniciaPiso(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Obtenemos el archivo de propiedades correspondiente al idioma que
		//tengamos seleccionado y que viene envuelto en facesContext
		ResourceBundle bundle = 
				facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		piso.setId(null);
		piso.setIdAgente(null);
		piso.setPrecio(0.0);
		piso.setDireccion(bundle.getString("valorDefectoDireccion"));
		piso.setCiudad(bundle.getString("valorDefectoCiudad"));
		piso.setAno(0);
		piso.setEstado(1);
		piso.setFoto(bundle.getString("fotoDefecto"));
	}
	public String listado() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Piso[]
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}
	}

	public void listadoCiudad() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Piso[]
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Piso[] pisos2;
		int j=0;
		for(int i=0;i<pisos.length;i++) {
			if(pisos[i].getCiudad().toString().equalsIgnoreCase(ciudad)) {
				j++;
			}
		}
		pisos2=new Piso[j];
		j=0;
		for(int i=0;i<pisos.length;i++) {
			if(pisos[i].getCiudad().toString().equalsIgnoreCase(ciudad)) {
				pisos2[j]=pisos[i];
				j++;
			}
		}
		if (pisos2.length!=0) {
			this.setPisos(pisos2);
	}
		
	}

	double precioMinimo=0.0;
	double precioMaximo=0.0;
	
	public double getPrecioMinimo() { return precioMinimo; }
	public void setPrecioMinimo(double precioMinimo) { this.precioMinimo = precioMinimo; }
	public double getPrecioMaximo() { return precioMaximo;}
	public void setPrecioMaximo(double precioMaximo) { this.precioMaximo = precioMaximo;}
	public void filtraPorPrecio() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Piso[]
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Piso[] pisos_filtrados;
		int a = 0;
		for(int i = 0; i < pisos.length;i++) {
			if((pisos[i].getPrecio() >= precioMinimo) && (pisos[i].getPrecio()<=precioMaximo)) {
				a++;
			}
		}
		pisos_filtrados = new Piso[a];
		a = 0;
		for(int i = 0; i < pisos.length; i++) {
			if((pisos[i].getPrecio() >= precioMinimo) && (pisos[i].getPrecio()<=precioMaximo)) {
				pisos_filtrados[a] = pisos[i];
				a++;
			}
		}
		this.setPisos(pisos_filtrados);
	}


	public String baja(Piso piso) {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosService();
			//Aliminamos el Piso seleccionado en la tabla
			service.deletePiso(piso.getId());
			//Actualizamos el javabean de Pisos inyectado en la tabla.
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	public String edit() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosService();
			//Recargamos el Piso seleccionado en la tabla de la base de datos por si hubiera cambios.
			piso = (BeanPiso) service.findById(piso.getId());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}

	public String salva() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosService();
			//Salvamos o actualizamos el Piso segun sea una operacion de alta o de edici��n
			if (piso.getId() == null) {
				service.savePiso(piso);
			}
			else {
				service.updatePiso(piso); 
			} 
			//Actualizamos el javabean de Pisos inyectado en la tabla
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
			this.listado();
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	//y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	//ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {    	  
		System.out.println("BeanPisos - PostConstruct"); 
		//Buscamos el Piso en la sesión. Esto es un patrón factoría claramente.
		ciudad = " ";
		piso = 
				(BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("piso"));
		//si no existe lo creamos e inicializamos
		if (pisos == null) { 
			System.out.println("BeanPisos - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "piso", piso);
		}
	}
	@PreDestroy
	public void end()  {
		System.out.println("BeanPisos - PreDestroy");
	}

}



