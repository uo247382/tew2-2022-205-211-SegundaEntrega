package com.tew.presentation;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.ClienteService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.PisosService;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.model.User;


@ManagedBean(name="pisosParaVisitar")
@SessionScoped
public class BeanPisosParaVisitar implements Serializable {
	private static final long serialVersionUID = 55556L;
	// Se añade este atributo de entidad para recibir el piso concreto selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.
	private PisoParaVisitar[] pisosParaVisitar = null;
	private String fecha = null;
	//uso de inyección de dependencia
	@ManagedProperty(value="#{PisoParaVisitar}") 
	private BeanPisoParaVisitar pisoParaVisitar;

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public BeanPisoParaVisitar getPisoParaVisitar() { 
		return pisoParaVisitar; 
	}
	public void setPisoParaVisitar(BeanPisoParaVisitar pisoParaVisitar) {
		this.pisoParaVisitar = pisoParaVisitar;
	}
	public PisoParaVisitar[] getPisosParaVisitar () {
		return(pisosParaVisitar);
	}
	public void setPisosParaVisitar(PisoParaVisitar[] pisosParaVisitar) {
		this.pisosParaVisitar = pisosParaVisitar;
	}

	public Long getIDCliente() {
		ClienteService service = Factories.services.createClienteService();
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap();
		User user = (User)session.get("LOGGEDIN_USER");
		String login = user.getLogin();
		try {
			List<Cliente> clientes = service.getClientes();
			for (Cliente cliente:clientes) {
				if (cliente.getLogin().equals(login)) {
					return cliente.getId();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void iniciaPisoParaVisitar(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Obtenemos el archivo de propiedades correspondiente al idioma que
		//tengamos seleccionado y que viene envuelto en facesContext
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		pisoParaVisitar.setP(null);
		pisoParaVisitar.setC(new Long(3));
		pisoParaVisitar.setFechaHoraCita(Timestamp.valueOf(LocalDateTime.now()));
		pisoParaVisitar.setEstado(1);
	}

	public String listadoCliente() {
		PisosParaVisitarService service;
		Long iDCliente = this.getIDCliente();
		try {

			System.out.println("entro para probar que hace el metodo listadoClientebbbbbbbbbbbbbbbbbbbbbbbbbbbb");
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Alumno[]
			pisosParaVisitar = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			PisoParaVisitar[] pisosParaVisitar_filtrados;
			int j = 0;
			for (int i = 0; i < pisosParaVisitar.length; i++) {
				if ((pisosParaVisitar[i].getC() == iDCliente)) {
					j++;
				}
			}
			pisosParaVisitar_filtrados = new PisoParaVisitar[j];
			j = 0;
			for(int i = 0; i < pisosParaVisitar.length; i++) {
				if((pisosParaVisitar[i].getC() == iDCliente)) {
					pisosParaVisitar_filtrados[j] = pisosParaVisitar[i];
					j++;
				}
			}
			this.setPisosParaVisitar(pisosParaVisitar_filtrados);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}	  
	}

	public String baja(PisoParaVisitar pisoParaVisitar) {
		PisosParaVisitarService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			//Aliminamos el piso seleccionado en la tabla
			service.deletePisoParaVisitar(pisoParaVisitar.getP(), pisoParaVisitar.getC());
			//Actualizamos el javabean de pisos inyectado en la tabla.
			pisosParaVisitar = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			return "exito";	
		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}	  
	}

	public String edit() {
		PisosParaVisitarService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			//Recargamos el alumno seleccionado en la tabla de la base de datos por si hubiera cambios.
			pisoParaVisitar = (BeanPisoParaVisitar) service.findById(pisoParaVisitar.getP(), pisoParaVisitar.getC());
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}	  
	}

	public String salva(Long iDPisoParaVisitar) {
		PisosParaVisitarService service; 
		try {
			Long iDCliente = getIDCliente();
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			if (compruebaID(iDPisoParaVisitar, iDCliente)) {
				return "error";
			}
			else {
				this.pisoParaVisitar.setP(iDPisoParaVisitar);
				this.pisoParaVisitar.setC(iDCliente);
			} 
			service.savePisoParaVisitar(pisoParaVisitar);
			//Actualizamos el javabean de pisos inyectado en la tabla
			pisosParaVisitar = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
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
		System.out.println("BeanPisosParaVisitar - PostConstruct"); 
		//Buscamos el piso en la sesión. Esto es un patrón factoría claramente.
		pisoParaVisitar = (BeanPisoParaVisitar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("pisoParaVisitar"));
		//si no existe lo creamos e inicializamos
		if (pisosParaVisitar == null) { 
			System.out.println("BeanPisosParaVisitar - No existia");
			pisoParaVisitar = new BeanPisoParaVisitar();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "pisoParaVisitar", pisoParaVisitar);
		}
	}

	@PreDestroy
	public void end()  {
		System.out.println("BeanPisosParaVisitar - PreDestroy");
	}   

	public String fijarFecha (Long iDPisoParaVisitar, Long iDCliente) {
		PisosParaVisitarService service;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = formatter.parse(getFecha());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.setTime(date);
			Long fecha = date.getTime();
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			//Salvamos o actualizamos el piso segun sea una operacion de alta o de edici��n
			if (!compruebaEstado3(iDPisoParaVisitar, iDCliente)) {
				return "error";
			}
			else {
				this.pisoParaVisitar.setEstado(2);


				this.pisoParaVisitar.setFechaHoraCita(new Timestamp(fecha));
				this.pisoParaVisitar.setP(iDPisoParaVisitar);
				this.pisoParaVisitar.setC(iDCliente);
				service.updatePisoParaVisitar2(pisoParaVisitar);
			}
			//Actualizamos el javabean de Pisos inyectado en la tabla
			pisosParaVisitar = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String confirma (Long iDPisoParaVisitar) {
		PisosParaVisitarService service;
		try {
			Long iDCliente = getIDCliente();
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			if (!compruebaEstado3(iDPisoParaVisitar, iDCliente) || !compruebaEstado1(iDPisoParaVisitar, iDCliente)) {
				return "error";
			}
			pisoParaVisitar.setP(iDPisoParaVisitar);
			pisoParaVisitar.setC(iDCliente);
			pisoParaVisitar.setEstado(3);
			service.updatePisoParaVisitar3(pisoParaVisitar);
			//Actualizamos el javabean de pisos inyectado en la tabla
			pisosParaVisitar = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public boolean compruebaID(Long iD1, Long iD2) {
		PisosParaVisitarService service = Factories.services.createPisosParaVisitarService();
		try {
			service.findById(iD1, iD2);
		} catch (EntityNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean compruebaEstado3(Long iD1, Long iD2) throws Exception{
		boolean aux = compruebaID(iD1,iD2);
		PisosParaVisitarService service = Factories.services.createPisosParaVisitarService();
		PisoParaVisitar pisoParaVisitar = service.findById(iD1, iD2);
		if(pisoParaVisitar.getEstado()!=3) {
			aux = true;
		}
		else {
			aux = false;
		}
		System.out.print(aux);
		return aux;
	}

	public boolean compruebaEstado1(Long iD1, Long iD2) throws Exception{
		boolean aux = compruebaID(iD1,iD2);
		PisosParaVisitarService service = Factories.services.createPisosParaVisitarService();
		PisoParaVisitar pisoParaVisitar = service.findById(iD1, iD2);
		if(pisoParaVisitar.getEstado()!=1) {
			aux = true;
		}
		else {
			aux = false;
		}
		System.out.print(aux);
		return aux;
	}

	public String listado() {
		PisosParaVisitarService service;


		try {
			// Acceso a la implementacion de la capa de negocio 
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosParaVisitarService();
			// De esta forma le damos informaciï¿½ï¿½n a toArray para poder hacer el casting a Alumno[]

			pisosParaVisitar = (PisoParaVisitar [])service.getPisosParaVisitarAgente().toArray(new PisoParaVisitar[0]);
			System.out.println("--------------------------------------------------------------------");
			System.out.println(pisosParaVisitar[0].getC());				
			System.out.println(pisosParaVisitar[0].getP());
			System.out.println("--------------------------------------------------------------------");
			System.out.println(pisosParaVisitar[1].getC());				
			System.out.println(pisosParaVisitar[1].getP());
			System.out.println("--------------------------------------------------------------------");
			System.out.println("exitoListado");


			return "exitoListado";
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.print("--------------------------------------------------------------------");
			return "error";
		}  
	}


}
