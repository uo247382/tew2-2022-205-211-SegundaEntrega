package com.tew.presentation;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AgenteService;
import com.tew.business.ClienteService;
import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.User;

@ManagedBean(name = "controllerPisos")
@SessionScoped
public class BeanPisos implements Serializable{
	public Piso[] getListadoPisoAgente() {
		return listadoPisoAgente;
	}
	public void setListadoPisoAgente(Piso[] listadoPisoAgente) {
		this.listadoPisoAgente = listadoPisoAgente;
	}

	private static final long serialVersionUID = 55555L;
	// Se aÃ±ade este atributo de entidad para recibir el alumno concreto selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.
	private Piso[] pisos = null;
	private String ciudad = null;
	//uso de inyecciÃ³n de dependencia
	@ManagedProperty(value="#{piso}") 
	private BeanPiso piso;
	String precioMinimo="0.0";
	String precioMaximo="0.0";
	private Piso[] pisosClienteCiudad = null;
	private Piso[] pisosClientePrecio = null;

	private Piso[] listadoPisoAgente = null;

	public Piso[] getPisosClientePrecio() {
		return pisosClientePrecio;
	}
	public void setPisosClientePrecio(Piso[] pisosClientePrecio) {
		this.pisosClientePrecio = pisosClientePrecio;
	}

	public Piso[] getPisosClienteCiudad() {
		return pisosClienteCiudad;
	}
	public void setPisosClienteCiudad(Piso[] pisosClienteCiudad) {
		this.pisosClienteCiudad = pisosClienteCiudad;
	}

	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public BeanPiso getPiso() { 
		return piso; 
	}
	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}	  
	public Piso[] getPisos () {
		return(pisos);
	}
	public void setPisos(Piso[] pisos) {
		this.pisos = pisos;
	}  
	public String getPrecioMinimo() { 
		return precioMinimo; 
	}
	public void setPrecioMinimo(String precioMinimo) { 
		this.precioMinimo = precioMinimo; 
	}
	public String getPrecioMaximo() { 
		return precioMaximo;
	}
	public void setPrecioMaximo(String precioMaximo) { 
		this.precioMaximo = precioMaximo;
	}

	public void iniciaPiso(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Obtenemos el archivo de propiedades correspondiente al idioma que
		//tengamos seleccionado y que viene envuelto en facesContext
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		piso.setId(null);
		piso.setIDAgente(null);
		piso.setPrecio(0.0);
		piso.setDireccion(bundle.getString("valorDefectoDireccion"));
		piso.setCiudad(bundle.getString("valorDefectoCiudad"));
		piso.setAno(0);
		piso.setEstado(1);
	}

	public String listado() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaciï¿½ï¿½n a toArray para poder hacer el casting a Alumno[]
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}  
	}
	
	public String listadoSinDuplicar() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaciï¿½ï¿½n a toArray para poder hacer el casting a Alumno[]
			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}  
	}

	public String listadoCliente() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a través de la factoría
			service = Factories.services.createPisosService();
			// Asi le damos información a toArray para poder hacer el casting a Pisos[]
			pisosClienteCiudad = (Piso [])service.getPisosCliente(this.getIDCliente()).toArray(new Piso[0]);
			pisosClientePrecio = (Piso [])service.getPisosCliente(this.getIDCliente()).toArray(new Piso[0]);
			// Almacenamos la lista original
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	private Long getIDCliente() {
		try {
			Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			User usuario = (User) session.get("LOGGEDIN_USER");
			String loginUsuarioActual = usuario.getLogin();
			ClienteService clientesService = Factories.services.createClienteService();
			List<Cliente> clientes = clientesService.getClientes();
			ListIterator<Cliente> iter = clientes.listIterator();
			while(iter.hasNext())
			{
				Cliente aux = iter.next();
				String loginActual = aux.getLogin();
				Long IDActual = aux.getId();
				if(loginActual.equalsIgnoreCase(loginUsuarioActual))
				{
					return IDActual;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void listadoCiudad() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaciï¿½ï¿½n a toArray para poder hacer el casting a Alumno[]
			List<Piso> auxiliar = service.getPisosClienteCiudad(this.getCiudad(), this.getIDCliente());
			// En caso de que la b�squeda no ofrezca resultados
			if(auxiliar.isEmpty()) {
				// Mostramos todos los pisos
				pisosClienteCiudad = (Piso [])service.getPisosCliente(this.getIDCliente()).toArray(new Piso[0]);
			}
			// En caso de que si muestre resultados
			else {
				// Los mostramos (a�adimos el % para que la busqueda sea predictiva)
				pisosClienteCiudad = (Piso [])service.getPisosClienteCiudad(this.getCiudad()+"%", this.getIDCliente()).toArray(new Piso[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		}
	}

	public void filtraPorPrecio() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a través de la factoría
			service = Factories.services.createPisosService();
			// Asi le damos información a toArray para poder hacer el casting a Pisos[]
			//pisosClientePrecio = (Piso [])service.getPisosClientePrecio(Integer.parseInt(this.getMinPrecio()), Integer.parseInt(this.getMaxPrecio())).toArray(new Piso[0]);
			List<Piso> auxiliar = service.getPisosClientePrecio(Double.parseDouble(this.getPrecioMinimo()), Double.parseDouble(this.getPrecioMaximo()), this.getIDCliente());
			// En caso de que la b�squeda no ofrezca resultados
			if(auxiliar.isEmpty()) {
				// Mostramos todos los pisos
				pisosClientePrecio = (Piso [])service.getPisosCliente(this.getIDCliente()).toArray(new Piso[0]);
			}
			// En caso de que si muestre resultados
			else {
				// Los mostramos
				pisosClientePrecio = (Piso [])service.getPisosClientePrecio(Double.parseDouble(this.getPrecioMinimo()), Double.parseDouble(this.getPrecioMaximo()), this.getIDCliente()).toArray(new Piso[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String baja(Piso piso) {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			//Aliminamos el piso seleccionado en la tabla
			service.deletePiso(piso.getId());
			//Actualizamos el javabean de pisos inyectado en la tabla.
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
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			//Recargamos el piso seleccionado en la tabla de la base de datos por si hubiera cambios.
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
			System.out.println("entro a salvar piso");
			// Acceso a la implementacion de la capa de negocio 
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			//Salvamos o actualizamos el piso segun sea una operacion de alta o de ediciï¿½ï¿½n
			if (piso.getId() == null) {
				service.savePisoAgente(this.getIDAgente(), piso);
			}
			else {
				System.out.println("piso entro a actualizar");
				System.out.println(piso.getId());
				service.updatePiso(piso); 

			} 
			//Actualizamos el javabean de pisos inyectado en la tabla
			listadoPisoAgente = (Piso [])service.getPisosAgente(this.getIDAgente()).toArray(new Piso[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	//y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesiÃ³n)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavÃ­a si el Managed Bean
	//ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {    	  
		System.out.println("BeanPisos - PostConstruct"); 
		ciudad = "";
		//Buscamos el piso en la sesiÃ³n. Esto es un patrÃ³n factorÃ­a claramente.
		piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("piso"));
		//si no existe lo creamos e inicializamos
		if (piso == null) { 
			System.out.println("BeanPisos - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "piso", piso);
		}
	}
	@PreDestroy
	public void end()  {
		System.out.println("BeanPisos - PreDestroy");
	}

	public String duplicar(Piso piso) {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a través de la factoría
			service = Factories.services.createPisosService();
			//Salvamos o actualizamos el alumno segun sea una operacion de alta o de edición

			service.duplicarPiso(piso); 

			//Actualizamos el javabean de alumnos inyectado en la tabla
			listadoPisoAgente = (Piso [])service.getPisosAgente(this.getIDAgente()).toArray(new Piso[0]);

			return "pisoDuplicado";

		} catch (Exception e) {
			e.printStackTrace();

			return "error";
		}
	}
	
	
	private Long getIDAgente() {
		// TODO Auto-generated method stub
		try {
			// Obtenemos la sesi�n y todos sus datos
			Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			// Obtenemos el usuario actual
			User usuario = (User) session.get("LOGGEDIN_USER");
			// Obtenemos el login de nuestro usuario
			String loginUsuarioActual = usuario.getLogin();
			// Cargamos el servicio con los m�todos necesarios para operar sobre los clientes
			AgenteService agentesService = Factories.services.createAgenteService();
			// Obtenemos todos los clientes para ver si nuestro usuario es uno de ellos
			List<Agente> agentes = agentesService.getAgentes();
			// Recorremos todos los clientes buscando si nuestro usuario es uno de ellos
			ListIterator<Agente> iter = agentes.listIterator();
			while(iter.hasNext())
			{
				Agente aux = iter.next();
				// Guardamos la referencia a nuestro cliente
				String loginActual = aux.getLogin();
				Long IDActual = aux.getId();
				// Verificamos si el cliente dado tiene el mismo login que nuestro usuario
				if(loginActual.equalsIgnoreCase(loginUsuarioActual))
				{
					// En caso de que coincida, nuestro usuario es un cliente y retornamos su ID
					return IDActual;
				}
			}


		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

}



