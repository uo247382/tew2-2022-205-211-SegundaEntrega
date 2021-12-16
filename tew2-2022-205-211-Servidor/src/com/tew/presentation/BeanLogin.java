package com.tew.presentation;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.PersistenceException;

import impl.tew.persistence.AgenteJdbcDao;
@ManagedBean(name="login")
public class BeanLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5738070263180281810L;
	private String name;
	private String password;

	User user = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String verify() {
		// necesario para accede a msgs y a los mensajes en español e ingles de los ficheros
		// de propiedades
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();
		user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			return "success";
		}
		// si el usuario no se encuentra
		// se prepara el mensaje que saldra en la vista del cliente
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				bundle.getString("login_form_result_error"), null);
		// se añade al element con id=”msg”
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}
	
	private void putUserInSession(User user) {
		Map<String, Object> session =
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}

	public void nuevoClienteLogin() {
		 
	}
	
	public String logout() {
		//FacesContext context = FacesContext.getCurrentInstance();
		//context.getExternalContext().invalidateSession();
		if(user != null) {
			Map<String, Object> session =
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session.remove("LOGGEDOUT_USER", user);
			return "exito";
		}
		return "error";
	}
	
	//Metodo cuando quieres cerrar la sesion
	public void resetBaseDatos() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.clear();
		
		
		ejecutaSQL("delete from pisos_para_visitar");
		ejecutaSQL("delete from Pisos");
		ejecutaSQL("delete from Agentes");
		ejecutaSQL("delete from Clientes");
		
		
		ejecutaSQL("INSERT INTO AGENTES VALUES(3,'agente1@micorreo.com','clave1')");
		ejecutaSQL("INSERT INTO AGENTES VALUES(4,'agente2@micorreo.com','clave2')");
		
		ejecutaSQL("INSERT INTO CLIENTES VALUES(3,'user1@micorreo.com','clave1','JOSE CARLOS','DIAZ RUENEZ','user1@micorreo.com')"); 
		ejecutaSQL("INSERT INTO CLIENTES VALUES(4,'user2@micorreo.com','clave2','PABLO','GONZALEZ GONZALEZ','user2@micorreo.com')");
		
		ejecutaSQL("INSERT INTO PISOS VALUES(1,3,235000,'CALLE PEDRO DUQUE','VALENCIA',2005,3, 'casa1.jpg')");
		ejecutaSQL("INSERT INTO PISOS VALUES(2,3,150000,'PLAZA REPUBLICA','BARCELONA',2017,5, 'casa2.jpg')");
		ejecutaSQL("INSERT INTO PISOS VALUES(3,4,500000,'AVENIDA DEL PRADO','MADRID',2010,5, 'casa3.jpg')");
		ejecutaSQL("INSERT INTO PISOS VALUES(4,4,75000,'RUTA DE LA APARICION','SEVILLA',2001,2, 'casa4.jpg')");
		
		ejecutaSQL("insert into PISOS_PARA_VISITAR values (1, 3, 1238123, 2)");
		ejecutaSQL("insert into PISOS_PARA_VISITAR values (2, 4, 1238123, 1)");
		
	}
	
	public void ejecutaSQL(String s) {
		PreparedStatement ps = null;
		Connection con = null;
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(s);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

	}
	


}
