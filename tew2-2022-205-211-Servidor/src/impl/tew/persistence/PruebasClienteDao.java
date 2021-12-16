package impl.tew.persistence;

import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class PruebasClienteDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClienteDao cliente = new ClienteJdbcDao();
		System.out.println(cliente.getClientes().get(0).getNombre());

		
		Cliente a = new Cliente();
		/*a.setLogin("maria23");
		a.setPasswd("pruebas2");
		a.setNombre("MARIA");
		a.setApellidos("SUAREZ LOPEZ");
		a.setEmail("maria@correo.es");
		try {
			cliente.save(a);
			System.out.println(cliente.getClientes().get(2).getLogin());
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*a = cliente.getClientes().get(2);
		a.setLogin("pruebas_2@gmail.com");
		try {
			cliente.update(a);
			System.out.println(cliente.getClientes().get(2).getLogin());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			cliente.delete(a.getId());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
