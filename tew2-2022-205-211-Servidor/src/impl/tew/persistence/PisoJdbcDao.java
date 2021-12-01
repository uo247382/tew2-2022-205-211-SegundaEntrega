package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Piso;
import com.tew.persistence.PisoDao;

import com.tew.persistence.exception.*;

/**
 * Implementaciï¿½ï¿½n de la interfaz de fachada al servicio de persistencia
 * para Alumnos. En este caso es Jdbc pero podrï¿½ï¿½a ser cualquier otra
 * tecnologia de persistencia, por ejemplo, la que veremos mï¿½ï¿½s adelante JPA
 * (mapeador de objetos a relacional)
 * 
 * @author Enrique
 *
 */
public class PisoJdbcDao implements PisoDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piso pisos = new Piso();
		List<Piso> listaPisos = new ArrayList<Piso>();

	}

	public List<Piso> getPisos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		System.out.println("holaaaa");
		List<Piso> pisos = new ArrayList<Piso>();
		
		System.out.println("entro a pedir el listado de pisos");

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setAno(rs.getInt("ANO"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setEstado(rs.getInt("ESTADO"));
				piso.setIDAgente(rs.getLong("IDAGENTE"));
				piso.setPrecio(rs.getInt("PRECIO"));
				
				pisos.add(piso);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
		return pisos;
	}

	@Override
	public void delete(Long id) throws NotPersistedException {
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		Connection con = null;
		int rows = 0;
		System.out.println("Prueba de que entro a borrar");
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps1 = con.prepareStatement("delete from pisosparavisitar where idpiso = ?");
			ps1.setLong(1, id);
			ps = con.prepareStatement("delete from pisos where id = ?");
			ps.setLong(1, id);
			
			rows = ps1.executeUpdate();
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Pisos " + id + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public Piso findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Piso piso = null;

		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisos where id = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {

				piso.setId(rs.getLong("ID"));
				piso.setAno(rs.getInt("ANO"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setEstado(rs.getInt("ESTADO"));
				piso.setIDAgente(rs.getLong("IDAGENTE"));
				piso.setPrecio(rs.getInt("PRECIO"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return piso;
	}

	@Override
	public void save(Piso a) throws AlreadyPersistedException {
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
			ps = con.prepareStatement("insert into pisos (id, idAgente, precio, direccion, ciudad, ano, estado) " + "values (?, ?, ?, ?, ?, ?, ?)");

			ps.setLong(1, a.getId());
			ps.setLong(2, a.getIDAgente());
			ps.setDouble(3,  a.getPrecio());
			ps.setString(4, a.getDireccion());
			ps.setString(5, a.getCiudad());
			ps.setInt(6, a.getAno());
			ps.setInt(7, a.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Pisos " + a + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public void updatePiso(Piso a) throws NotPersistedException {
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
			ps = con.prepareStatement("update pisos set idAgente = ?, precio = ?, direccion = ?, ciudad = ?, ano = ?, estado = ? where id = ?");

			ps.setLong(1, a.getIDAgente());
			ps.setDouble(2, a.getPrecio());
			ps.setString(3, a.getDireccion());
			ps.setString(4, a.getCiudad());
			ps.setInt(5, a.getAno());
			ps.setInt(6, a.getEstado());
			ps.setLong(7, a.getId());


			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Pisos " + a + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public List<Piso> getPisosAgente(Long idAgente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Piso> pisos = new ArrayList<Piso>();
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Pisos WHERE IDAgente = ?");		// PUBLIC.CLIENTES
			ps.setLong(1, idAgente);
			rs = ps.executeQuery();
			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIDAgente(rs.getLong("IDAGENTE"));
				piso.setPrecio(rs.getInt("PRECIO"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setAno(rs.getInt("ANO"));
				piso.setEstado(rs.getInt("ESTADO"));
				pisos.add(piso);
			}	
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
		return pisos;
	}

	@Override
	public List<Piso> getPisosCliente(Long idCliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Piso> pisos = new ArrayList<Piso>();
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM Pisos " + 
					  					"FULL JOIN PisosParaVisitar " + 
					  					"ON Pisos.ID = PisosParaVisitar.IDPiso");		// PUBLIC.CLIENTES
			rs = ps.executeQuery();
			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIDAgente(rs.getLong("IDAGENTE"));
				piso.setPrecio(rs.getInt("PRECIO"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setAno(rs.getInt("ANO"));
				piso.setEstado(rs.getInt("ESTADO"));
				// Buscamos si ese piso ya ha sido visitado 
				// viendo si el campo IDPISO de la consulta FULL JOIN nos devuelve vacio o no
				if(!(rs.getString("IDPISO") == null) && rs.getLong("IDCLIENTE") == idCliente)
				{
					// Si no nos devuelve vacio, significa que existe una visita previa
					// Entonces marcamos como verdadero que ha sido visitado en el campo interno de ese piso
					piso.setVisitado(true);
				}
				pisos.add(piso);
			}
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
		return pisos;
	}

	@Override
	public List<Piso> getPisosClienteCiudad(String Ciudad, Long idCliente) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Piso> pisosCiudad = new ArrayList<Piso>();
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM Pisos " + 
					  					"FULL JOIN PisosParaVisitar " + 
					  					"ON Pisos.ID = PisosParaVisitar.IDPiso " + 
					  					"WHERE Ciudad LIKE UPPER(?)");		// PUBLIC.CLIENTES
			ps.setString(1, Ciudad);
			rs = ps.executeQuery();
			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIDAgente(rs.getLong("IDAGENTE"));
				piso.setPrecio(rs.getInt("PRECIO"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setAno(rs.getInt("ANO"));
				piso.setEstado(rs.getInt("ESTADO"));
				// Buscamos si ese piso ya ha sido visitado 
				// viendo si el campo IDPISO de la consulta FULL JOIN nos devuelve vacio o no
				if(!(rs.getString("IDPISO") == null) && rs.getLong("IDCLIENTE") == idCliente)
				{
					// Si no nos devuelve vacio, significa que existe una visita previa
					// Entonces marcamos como verdadero que ha sido visitado en el campo interno de ese piso
					piso.setVisitado(true);
				}
				pisosCiudad.add(piso);
			}
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
		return pisosCiudad;
	}


	@Override
	public void savePisoAgente(Long idAgente, Piso piso) throws AlreadyPersistedException {
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
					"insert into PUBLIC.PISOS (IDAgente, Precio, Direccion, Ciudad, Año, Estado) " +
					"values (?, ?, ?, ?, ?, ?)");
			ps.setLong(1, idAgente);
			ps.setDouble(2, piso.getPrecio());
			ps.setString(3, piso.getDireccion());
			ps.setString(4, piso.getCiudad());
			ps.setInt(5, piso.getAno());
			ps.setInt(6, piso.getEstado());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Piso " + piso + " already persisted");
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
	public List<Piso> getPisosClientePrecio(Double minPrecio, Double maxPrecio, Long idCliente) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Piso> pisosPrecio = new ArrayList<Piso>();
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM Pisos " + 
					  					"FULL JOIN PisosParaVisitar " + 
					  					"ON Pisos.ID = PisosParaVisitar.IDPiso " + 
					  					"WHERE precio <= ? and precio >= ?");	// PUBLIC.CLIENTES
			ps.setDouble(1, maxPrecio);
			ps.setDouble(2, minPrecio);
			rs = ps.executeQuery();
			while (rs.next()) {	
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIDAgente(rs.getLong("IDAGENTE"));
				piso.setPrecio(rs.getInt("PRECIO"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setAno(rs.getInt("ANO"));
				piso.setEstado(rs.getInt("ESTADO"));
				// Buscamos si ese piso ya ha sido visitado 
				// viendo si el campo IDPISO de la consulta FULL JOIN nos devuelve vacio o no
				if(!(rs.getString("IDPISO") == null) && rs.getLong("IDCLIENTE") == idCliente)
				{
					// Si no nos devuelve vacio, significa que existe una visita previa
					// Entonces marcamos como verdadero que ha sido visitado en el campo interno de ese piso
					piso.setVisitado(true);
				}
				pisosPrecio.add(piso);
			}
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
		return pisosPrecio;
	}

	@Override
	public void duplicarPiso(Piso a) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLIC.PISOS (IDAgente, Precio, Direccion, Ciudad, Ano,Estado) " +
					"values (?, ?, ?, ?, ?, ?)");
			
			ps.setLong(1, a.getIDAgente());
			ps.setDouble(2, a.getPrecio());
			ps.setString(3, a.getDireccion());
			ps.setString(4, a.getCiudad());
			ps.setInt(5, a.getAno());
			ps.setInt(6, a.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Piso " + a + " already persisted");
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

}
