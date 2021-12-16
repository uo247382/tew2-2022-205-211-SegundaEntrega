package com.tew.presentation;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.ClienteService;
import com.tew.business.PisosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanClientes implements Serializable{
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el Piso concreto selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.


	private Cliente[] clientes = null;
    
	//uso de inyección de dependencia
	@ManagedProperty(value="#{cliente}") 
	private BeanCliente cliente;
	public BeanCliente getCliente() { return cliente; }
	public void setCliente(BeanCliente cliente) {this.cliente = cliente;}


	/* public BeanPisos()
          {
        	  iniciaPiso(null);
          }*/

	public Cliente[] getClientes() {
		return(clientes);
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}  
	public void iniciaCliente(ActionEvent event) {
		//Obtenemos el archivo de propiedades correspondiente al idioma que
		//tengamos seleccionado y que viene envuelto en facesContext
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = 
	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
	    cliente.setId(null);
	    cliente.setLogin(bundle.getString("loginClienteDefecto"));
	    cliente.setPasswd(bundle.getString("passwdClienteDefecto"));
	    cliente.setNombre(bundle.getString("nombreClienteDefecto"));
	    cliente.setApellidos(bundle.getString("apellidosClienteDefecto"));
	    cliente.setEmail(bundle.getString("emailClienteDefecto"));
	}
	public String listado() {
		ClienteService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createClienteService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Piso[]
			clientes = (Cliente [])service.getClientes().toArray(new Cliente[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	public String baja(Piso piso) {
		ClienteService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createClienteService();
			//Aliminamos el Piso seleccionado en la tabla
			service.deleteCliente(cliente.getId());
			//Actualizamos el javabean de Pisos inyectado en la tabla.
			clientes = (Cliente [])service.getClientes().toArray(new Cliente[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	public String edit() {
		ClienteService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createClienteService();
			//Recargamos el Piso seleccionado en la tabla de la base de datos por si hubiera cambios.
			cliente = (BeanCliente) service.findById(cliente.getId());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}

	public String salva() {
		ClienteService service;
		//para poder dar al cliente una sesion
		Map<String, Object> sesiones = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		User usuario = (User) sesiones.get("LOGGEDIN_USER");
		if(usuario == null) {
			User nuevouser = new User(cliente.getLogin(), cliente.getNombre());
			sesiones.put("LOGGEDIN_USER", nuevouser);
		}
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createClienteService();
			//Salvamos o actualizamos el Piso segun sea una operacion de alta o de edici��n
			if (cliente.getId()==null) {
				
				service.saveCliente(cliente);
			}
			else {
				service.updateCliente(cliente);
			} 
			//Actualizamos el javabean de Pisos inyectado en la tabla
			clientes = (Cliente [])service.getClientes().toArray(new Cliente[0]);
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
		System.out.println("BeanClientes - PostConstruct"); 
		//Buscamos el Piso en la sesión. Esto es un patrón factoría claramente.
		cliente = 
				(BeanCliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("cliente"));
		//si no existe lo creamos e inicializamos
		if (clientes == null) { 
			System.out.println("BeanClientes - No existia");
			cliente = new BeanCliente();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "cliente",cliente);
		}
	}
	@PreDestroy
	public void end()  {
		System.out.println("BeanClientes - PreDestroy");
	}

}
