package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisosParaVisitarDao;
import com.tew.persistence.exception.*;


/**
 * Implementaciï¿½ï¿½n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podrï¿½ï¿½a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos mï¿½ï¿½s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author Enrique
 *
 */
public class PisosParaVisitarJdbcDao implements PisosParaVisitarDao {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PisoParaVisitar pisoParaVisitar = new PisoParaVisitar();
		List<PisoParaVisitar> listaPisosParaVisitar = new ArrayList<PisoParaVisitar>(); 
		
	}
	
	
	public List<PisoParaVisitar> getPisosParaVisitar() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<PisoParaVisitar> pisosParaVisitar = new ArrayList<PisoParaVisitar>();

		System.out.println("entro a pedir el listado de pisos para visitar");
		
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			System.out.println("me conecto lo siguiente envio la peticion a pisosParaVisitar");

			ps = con.prepareStatement("select * from pisosParavisitar");
			rs = ps.executeQuery();

			while (rs.next()) {
				
				PisoParaVisitar pisoParaVisitar = new PisoParaVisitar();
				pisoParaVisitar.setC(rs.getLong("idcliente"));
				pisoParaVisitar.setEstado(rs.getInt("estadoVisita"));
				pisoParaVisitar.setFechaHoraCita(rs.getTimestamp("fechahoraCita"));
				pisoParaVisitar.setP(rs.getLong("idpiso"));
				System.out.print(pisoParaVisitar);

				pisosParaVisitar.add(pisoParaVisitar);
			}
			System.out.println("DESPUES DE AÑADIR A LA LISTA DE PISOS SALGO DEL WHILE");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		System.out.println("devuelvo algo");
		System.out.print(pisosParaVisitar);

		return pisosParaVisitar;
	}

	@Override
	public void delete(Long id, Long idCliente) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from pisoparavisitar where idpiso = ? and idcliente = ?");
			
			ps.setLong(1, id);
			ps.setLong(2, idCliente);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso para Visitar: " + id + " not found");
			} 
			
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

	@Override
	public PisoParaVisitar findById(Long id, Long idCliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		PisoParaVisitar pisoParaVisitar = null;
		System.out.println(id);
		System.out.println(idCliente);
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisoparavisitar where idPiso = ? and idCliente = ?");
			ps.setLong(1, id);
			ps.setLong(2, idCliente);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				pisoParaVisitar = new PisoParaVisitar();
				
				pisoParaVisitar.setC(rs.getLong("cliente"));
				pisoParaVisitar.setEstado(rs.getInt("estado"));
				pisoParaVisitar.setFechaHoraCita(rs.getTimestamp("fechahoraCita"));
				pisoParaVisitar.setP(rs.getLong("piso"));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return pisoParaVisitar;
	}

	@Override
	public void save(PisoParaVisitar a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into pisoParaVisitar (idPiso, idCliente, fechaHoraCita, estadoVisita) " +
					"values (?, ?, ?, ?)");
			
			ps.setLong(1, a.getP());
			ps.setLong(2, a.getC());
			ps.setTimestamp(3, a.getFechaHoraCita());
			ps.setInt(4, a.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("PisoParaVisitar " + a + " already persisted");
			} 

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

	@Override
	public void  update2(PisoParaVisitar pisoParaVisitar) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update pisosparavisitar " +
					"set idCliente = ?, fechacitahora = ?, estadoCita = 2" +
					"where idpiso = ?");
			
			ps.setLong(1, pisoParaVisitar.getC());
			ps.setTimestamp(2, pisoParaVisitar.getFechaHoraCita());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("pisoParaVisitar " + pisoParaVisitar + " not found");
			} 
			
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


	@Override
	public void  update3(PisoParaVisitar pisoParaVisitar) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update pisosparavisitar " +
					"set idCliente = ?, fechacitahora = ?, estadoCita = 3" +
					"where idpiso = ?");
			
			ps.setLong(1, pisoParaVisitar.getC());
			ps.setTimestamp(2, pisoParaVisitar.getFechaHoraCita());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("pisoParaVisitar " + pisoParaVisitar + " not found");
			} 
			
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
	
	public List<PisoParaVisitar> getPisosParaVisitarAgente() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<PisoParaVisitar> pisosParaVisitar = new ArrayList<PisoParaVisitar>();

		System.out.println("entro a pedir el listado de pisos para visitar");
		
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			System.out.println("me conecto lo siguiente envio la peticion a pisosParaVisitarAgente");

			ps = con.prepareStatement("SELECT * FROM PISOS join PISOSPARAVISITAR on ID=IDPISO WHERE ESTADOVISITA=1 AND IDAGENTE=1\n");
			rs = ps.executeQuery();

			while (rs.next()) {
				
				PisoParaVisitar pisoParaVisitar = new PisoParaVisitar();
				pisoParaVisitar.setC(rs.getLong("idcliente"));
				pisoParaVisitar.setEstado(rs.getInt("estadoVisita"));
				pisoParaVisitar.setFechaHoraCita(rs.getTimestamp("fechahoraCita"));
				pisoParaVisitar.setP(rs.getLong("idpiso"));
				System.out.print("piso: "+pisoParaVisitar.getP()+"cliente: "+pisoParaVisitar.getC()+pisoParaVisitar.getEstado());
				System.out.println(pisoParaVisitar.getFechaHoraCita());

				pisosParaVisitar.add(pisoParaVisitar);
			}
			System.out.println("DESPUES DE AÑADIR A LA LISTA DE PISOS SALGO DEL WHILE");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		System.out.println("devuelvo algo");
		System.out.println(pisosParaVisitar);

		return pisosParaVisitar;
	}


}
