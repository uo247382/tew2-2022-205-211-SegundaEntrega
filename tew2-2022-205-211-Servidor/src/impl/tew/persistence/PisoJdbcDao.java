package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Piso;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.*;


/**
 * Implementaci��n de la interfaz de fachada al servicio de persistencia para
 * Pisos. En este caso es Jdbc pero podr��a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos m��s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author Enrique
 *
 */
public class PisoJdbcDao implements PisoDao {

	public List<Piso> getPisos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Piso> Pisos = new ArrayList<Piso>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Pisos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Piso Piso = new Piso();
				Piso.setId(rs.getLong("ID"));
				Piso.setIdAgente(rs.getLong("ID_AGENTE"));
				Piso.setPrecio(rs.getDouble("PRECIO"));
				Piso.setDireccion(rs.getString("DIRECCION"));
				Piso.setCiudad(rs.getString("CIUDAD"));
				Piso.setAno(rs.getInt("ANO"));
				Piso.setEstado(rs.getInt("ESTADO"));
				Piso.setFoto(rs.getString("FOTO"));
				Pisos.add(Piso);
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
		
		return Pisos;
	}

	public void delete(Long id) throws NotPersistedException {
		PreparedStatement ps = null;
		PreparedStatement ps3 = null;
		Connection con = null;
		int rows = 0;
		
		PreparedStatement ps2 = null;
		ResultSet r = null;
		int rows2 = 0;
		try {
			
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			
			ps = con.prepareStatement("delete from pisos where id = ?");
			
			ps3 = con.prepareStatement("select * from pisos_para_visitar where id_piso = ?");
			ps3.setLong(1, id);
			r = ps3.executeQuery();
			

			if(r!=null) {
				ps2 = con.prepareStatement("delete from pisos_para_visitar where id_piso = ?");
				ps2.setLong(1, id);
				ps2.executeUpdate();
				
			}
			if(r==null) {
				System.out.print("No habia entrada en pisos_para_visitar");
			}
			
			ps.setLong(1, id);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("piso " + id + " not found");
			} 
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (r != null) {try{ r.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (ps3 != null) {try{ps3.close(); } catch (Exception ex){}};
			if (ps2 != null) {try{ ps2.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	@Override
	public Piso findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Piso Piso = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PISOS where id = ?");
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				Piso = new Piso();
				
				Piso.setId(rs.getLong("ID"));
				Piso.setIdAgente(rs.getLong("ID_AGENTE"));
				Piso.setPrecio(rs.getDouble("PRECIO"));
				Piso.setDireccion(rs.getString("DIRECCION"));
				Piso.setCiudad(rs.getString("CIUDAD"));
				Piso.setAno(rs.getInt("ANO"));
				Piso.setEstado(rs.getInt("ESTADO"));
				Piso.setFoto(rs.getString("FOTO"));
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
		
		return Piso;
	}

	@Override
	public void save(Piso a) throws AlreadyPersistedException {
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
					"insert into PUBLIC.PISOS (id_agente, precio, direccion, ciudad, ano, estado, foto) " + 
					"values ( ?, ?, ?, ?, ?, ?, ?)");
			
			//System.out.println("Depuracio"); 
			//System.out.println();
			//System.out.println(ps);
			ps.setLong(1, a.getIdAgente());
			ps.setDouble(2, a.getPrecio());
			ps.setString(3, a.getDireccion());
			ps.setString(4, a.getCiudad());
			ps.setInt(5, a.getAno());
			ps.setInt(6, a.getEstado());
			ps.setString(7,  a.getFoto());

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

	@Override
	public void update(Piso a) throws NotPersistedException {
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
					"update Pisos " +
					"set id_agente = ?, precio = ?, direccion = ?, ciudad=?, ano=? ,estado=?, foto=?" +
					"where pisos.id = ?");
			
			
			ps.setLong(1, a.getIdAgente());
			ps.setDouble(2, a.getPrecio());
			ps.setString(3, a.getDireccion());
			ps.setString(4, a.getCiudad());
			ps.setInt(5, a.getAno());
			ps.setInt(6, a.getEstado());
			ps.setString(7, a.getFoto());
			ps.setLong(8, a.getId());
			
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + a + " not found");
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
