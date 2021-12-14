package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.tew.persistence.ReiniciarDataBaseDao;
import com.tew.persistence.exception.PersistenceException;

public class ReiniciarDataBaseJdbcDao implements ReiniciarDataBaseDao {

	@Override
	public void reinicia() {
		// TODO Auto-generated method stub
		resetBaseDatos();
	}
	
	public void resetBaseDatos() {
		//Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		//session.clear();
		
		
		//ejecutaSQL("delete from pisos_para_visitar");
		ejecutaSQL("delete from Pisos");
		ejecutaSQL("delete from Agentes");
		//ejecutaSQL("delete from Clientes");
		
		
		ejecutaSQL("INSERT INTO AGENTES VALUES(0,'Alfredo@agentes.com','Alfredo')");
		ejecutaSQL("INSERT INTO AGENTES VALUES(1,'Amanda@agentes.com','Amanda')");
		
		//ejecutaSQL("INSERT INTO CLIENTES VALUES(3,'user1@micorreo.com','clave1','JOSE CARLOS','DIAZ RUENEZ','user1@micorreo.com')"); 
		//ejecutaSQL("INSERT INTO CLIENTES VALUES(4,'user2@micorreo.com','clave2','PABLO','GONZALEZ GONZALEZ','user2@micorreo.com')");
		
		ejecutaSQL("INSERT INTO PISOS VALUES(0,0,200000,'Calle del Paraiso','Oviedo',1989,4,'foto3.jpg')");
		ejecutaSQL("INSERT INTO PISOS VALUES(1,0,3000,'Calle de Arriba','Oviedo',1989,2,'foto4.jpg')");
		ejecutaSQL("INSERT INTO PISOS VALUES(2,0,2230,'Calle de Abajo','Oviedo',1989,5,'foto5.jpg')");
		ejecutaSQL("INSERT INTO PISOS VALUES(3,0,5000,'Calle del Cuba','Aviles',1989,2,'foto6.jpg')");
		
		//ejecutaSQL("insert into PISOS_PARA_VISITAR values (1, 3, 1238123, 2)");
		//ejecutaSQL("insert into PISOS_PARA_VISITAR values (2, 4, 1238123, 1)");
		
	}
	
	public void ejecutaSQL(String s) {
		PreparedStatement ps = null;
		Connection con = null;
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
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
