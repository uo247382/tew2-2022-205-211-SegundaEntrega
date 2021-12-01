package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Agente;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.*;

public class AgenteJdbcDao implements AgenteDao {
	@Override
	public List<Agente> getAgentes() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Agente> agentes = new ArrayList<Agente>();
		System.out.println("prueba de que trae Agentes");
		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from agentes");
			rs = ps.executeQuery();
			while (rs.next()) {
				Agente agente = new Agente();
				agente.setId(rs.getLong("ID"));
				agente.setLogin(rs.getString("LOGIN"));
				agente.setPasswd(rs.getString("PASSWD"));
				agentes.add(agente);
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
		return agentes;
	}

	@Override
	public void delete(Long id) throws NotPersistedException {
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
			ps = con.prepareStatement("delete from agente where id = ?");
			ps.setLong(1, id);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Agente " + id + " not found");
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
	public Agente findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Agente agente = null;
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from agente where id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {	
				agente = new Agente();
				agente.setId(rs.getLong("ID"));
				agente.setLogin(rs.getString("LOGIN"));
				agente.setPasswd(rs.getString("PASSWD"));
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
		return agente;
	}

	@Override
	public void save(Agente a) throws AlreadyPersistedException {
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
					"insert into agente (id, login, passwd) " +
					"values (?, ?, ?)");
			ps.setLong(1, a.getId());
			ps.setString(2, a.getLogin());
			ps.setString(3, a.getPasswd());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("agente " + a + " already persisted");
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
	public void update(Agente a) throws NotPersistedException {
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
					"update agente " +
					"set id = ?, login = ?, passwd = ?" +
					"where id = ?");
			ps.setLong(1, a.getId());
			ps.setString(2, a.getLogin());
			ps.setString(3, a.getPasswd());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("agente " + a + " not found");
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
	public void reset() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement tablaAgentes = null; PreparedStatement tablaClientes = null; 
		PreparedStatement tablaPisosParaVisitar = null; PreparedStatement tablaPisos = null;
		Connection con = null;
		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			tablaAgentes = con.prepareStatement("delete from Agentes");
			tablaClientes = con.prepareStatement("delete from Clientes");
			tablaPisosParaVisitar = con.prepareStatement("delete from pisos_para_visitar");
			tablaPisos = con.prepareStatement("delete from pisos");
			tablaAgentes.executeUpdate();
			tablaClientes.executeUpdate();
			tablaPisosParaVisitar.executeUpdate();
			tablaPisos.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (tablaAgentes != null) {try{ tablaAgentes.close(); } catch (Exception ex){}};
			if (tablaClientes != null) {try{ tablaClientes.close(); } catch (Exception ex){}};
			if (tablaPisosParaVisitar != null) {try{ tablaPisosParaVisitar.close(); } catch (Exception ex){}};
			if (tablaPisos != null) {try{ tablaPisos.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
}
