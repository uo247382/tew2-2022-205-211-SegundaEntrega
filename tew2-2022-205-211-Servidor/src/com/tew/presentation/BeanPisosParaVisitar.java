package com.tew.presentation;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AgenteService;
import com.tew.business.ClienteService;
import com.tew.business.PisoParaVisitarService;
import com.tew.business.PisosService;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanPisosParaVisitar implements Serializable{
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el Piso concreto selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.


	private String fechaString = null;
	public String getFechaString() {
		return fechaString;
	}
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	private PisoParaVisitar[] pisosv = null;

	//uso de inyección de dependencia
	@ManagedProperty(value="#{PisoParaVisitar}") 
	private BeanPisoParaVisitar pisoParaVisitar;
	
	public BeanPisoParaVisitar getPiso() { return pisoParaVisitar; }
	public void setPisoParaVisitar(BeanPisoParaVisitar piso) {this.pisoParaVisitar = piso;}

	
	/* public BeanPisos()
          {
        	  iniciaPiso(null);
          }*/

	public PisoParaVisitar[] getPisos() {
		return(pisosv);
	}

	public void setPisos(PisoParaVisitar[] pisos) {
		this.pisosv = pisos;
	}
	
	public Long getIdCliente() {
        ClienteService service=Factories.services.createClienteService();
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        User u=(User)session.get("LOGGEDIN_USER");
        String login=u.getLogin();
        try {
            List<Cliente> clientes=service.getClientes();
            for(Cliente c:clientes) {
                if(c.getLogin().equals(login)) {
                    return c.getId();
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public Long getIdAgente() {
        AgenteService service=Factories.services.createAgenteService();
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        User u=(User)session.get("LOGGEDIN_USER");
        String login=u.getLogin();
        try {
            List<Agente> agentes=service.getAgentes();
            for(Agente a:agentes) {
                if(a.getLogin().equals(login)) {
                    return a.getId();
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	public String listadoCliente() {
		PisoParaVisitarService service;
		Long idc = this.getIdCliente();
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Piso[]
			pisosv = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			PisoParaVisitar[] pisos_filtrados;
			int a = 0;
			for(int i = 0; i < pisosv.length;i++) {
				if((pisosv[i].getC() == idc) ) {
					a++;
				}
			}
			pisos_filtrados = new PisoParaVisitar[a];
			a = 0;
			for(int i = 0; i < pisosv.length; i++) {
				if((pisosv[i].getC() == idc)) {
					pisos_filtrados[a] = pisosv[i];
					a++;
				}
			}
			this.setPisos(pisos_filtrados);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}
	}
	public String listadoAgente() {
		PisoParaVisitarService service;
		PisosService serviceP;
		Long ida = this.getIdAgente();
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			serviceP =Factories.services.createPisosService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a Piso[]
			pisosv = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			Piso p = serviceP.findById(pisosv[0].getP());
			System.out.println(p.getIdAgente());
			PisoParaVisitar[] pisos_filtrados;
			int a = 0;
			for(int i = 0; i < pisosv.length;i++) {
				p = serviceP.findById(pisosv[i].getP());
				if(p.getIdAgente()==ida) {
					a++;
				}
			}
			pisos_filtrados = new PisoParaVisitar[a];
			a = 0;
			for(int i = 0; i < pisosv.length; i++) {
				if((serviceP.findById(pisosv[i].getP() ).getIdAgente()==ida)) {
					pisos_filtrados[a] = pisosv[i];
					a++;
				}
			}
			this.setPisos(pisos_filtrados);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}
	}

	


	public String baja(PisoParaVisitar piso) {
		PisoParaVisitarService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			//Aliminamos el Piso seleccionado en la tabla
			service.deletePisoParaVisitar(piso.getP(), piso.getC());
			//Actualizamos el javabean de Pisos inyectado en la tabla.
			pisosv = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	public String edit() {
		PisoParaVisitarService service;
		
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			//Recargamos el Piso seleccionado en la tabla de la base de datos por si hubiera cambios.
			pisoParaVisitar = (BeanPisoParaVisitar) service.findByIds(pisoParaVisitar.getP(), pisoParaVisitar.getC());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	public String fijarFecha (Long idp, Long idc) {
		PisoParaVisitarService service;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		try {
			Long ida = this.getIdAgente();
			Date d1= formatter.parse(getFechaString());
			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			cal.setTime(d1);
			Long f = d1.getTime();
			
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
		
			
			//Salvamos o actualizamos el Piso segun sea una operacion de alta o de edici��n
			if (!compruebaEstado3(idp,idc)) {
				return "error";
			
			}
			else {
				this.pisoParaVisitar.setEstado(2);
				this.pisoParaVisitar.setFechaHoraCita(f);
				this.pisoParaVisitar.setP(idp);
				this.pisoParaVisitar.setC(idc);
				
				service.updatePisoParaVisitar2(pisoParaVisitar);
				
			} 
			
			//Actualizamos el javabean de Pisos inyectado en la tabla
			pisosv = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			this.listadoAgente();
			
			return "exito";
			}
			catch (Exception e) {
				e.printStackTrace();  
				return "error";
			}
		
	}
	public String confirma (Long idp) {
		PisoParaVisitarService service;
		
		try {
			Long idc = getIdCliente();
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			if (!compruebaEstado3(idp,idc) || !compruebaEstado1(idp,idc)) {
				return "error";
			
			}
			
				pisoParaVisitar.setP(idp);
				pisoParaVisitar.setC(idc);
				pisoParaVisitar.setEstado(3);
				service.updatePisoParaVisitar3(pisoParaVisitar);
				
			
			//Actualizamos el javabean de Pisos inyectado en la tabla
			pisosv = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}

	public String salva(Long idp) {
		PisoParaVisitarService service;
		
		try {
			Long idc = getIdCliente();
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createPisosParaVisitarService();
			if(compruebaID(idp,idc)) {
				return "error";
			}
			else {
				this.pisoParaVisitar.setP(idp);
				this.pisoParaVisitar.setC(idc);
			}
				service.savePisoParaVisitar(pisoParaVisitar);
				
			
		
			pisosv = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	public void iniciaPisoParaVisitar(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    pisoParaVisitar.setP(null);
    	    pisoParaVisitar.setC(new Long(3));
    	    pisoParaVisitar.setFechaHoraCita(0L);
    	    pisoParaVisitar.setEstado(1);
	  }	      
	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	//y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	//ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {    	  
		System.out.println("BeanPisos - PostConstruct"); 
		//Buscamos el Piso en la sesión. Esto es un patrón factoría claramente.
		pisoParaVisitar = 
				(BeanPisoParaVisitar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("pisoParaVisitar"));
		//si no existe lo creamos e inicializamos
		if (pisosv == null) { 
			System.out.println("BeanPisos - No existia");
			pisoParaVisitar = new BeanPisoParaVisitar();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "pisoParaVisitar", pisoParaVisitar);
		}
	}
	@PreDestroy
	public void end()  {
		System.out.println("BeanPisos - PreDestroy");
	}
	public boolean compruebaID(Long id1, Long id2) {
	      PisoParaVisitarService service = Factories.services.createPisosParaVisitarService();
	      try {
	        service.findByIds(id1, id2);
	    } catch (EntityNotFoundException e) {
	        return false;
	    }
	      return true;

	  }
	
	public boolean compruebaEstado3(Long id1, Long id2) throws Exception{
		boolean b = compruebaID(id1,id2);
		PisoParaVisitarService service = Factories.services.createPisosParaVisitarService();
		PisoParaVisitar pv = service.findByIds(id1, id2);
		if(pv.getEstado()!=3) {
				b = true;
			}
		else b = false;
		System.out.print(b);
		return b;
	}
	
	public boolean compruebaEstado1(Long id1, Long id2) throws Exception{
		boolean b = compruebaID(id1,id2);
		PisoParaVisitarService service = Factories.services.createPisosParaVisitarService();
		PisoParaVisitar pv = service.findByIds(id1, id2);
		if(pv.getEstado()!=1) {
				b = true;
			}
		else b = false;
		System.out.print(b);
		return b;
	}

}




