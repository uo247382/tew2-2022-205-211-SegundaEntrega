package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.*;

public class ClienteJdbcDao implements ClienteDao {
	@Override
	public List<Cliente> getClientes() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		System.out.println("prueba de que trae Clientes");

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Clientes");
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("ID"));
				cliente.setLogin(rs.getString("LOGIN"));
				cliente.setPasswd(rs.getString("PASSWD"));
				cliente.setNombre(rs.getString("NOMBRE"));
				cliente.setApellidos(rs.getString("APELLIDOS"));
				cliente.setEmail(rs.getString("EMAIL"));
				clientes.add(cliente);
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
		return clientes;
	}

	@Override
	public void save(Cliente cliente) throws AlreadyPersistedException {
		
	}

	@Override
	public void update(Cliente cliente) throws NotPersistedException {
		
	}

	@Override
	public void delete(Long id) throws NotPersistedException {
		
	}

	@Override
	public Cliente findById(Long id) {
		return null;
	}
}
