package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class ClientesOperaciones {

	public void save(Cliente c) throws EntityAlreadyExistsException{
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.save(c);
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityAlreadyExistsException("Cliente ya existe " + c, e);
		}
	}
	
	public void delete(Long id) throws EntityNotFoundException{
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.delete(id);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("Cliente no existe " + id, e);
		}
	}
	
	public Cliente find(Long id) throws EntityNotFoundException{
		ClienteDao dao = Factories.persistence.createClienteDao();
		Cliente a = dao.findById(id);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el alumno");
		}
		
		return a;
	}
	
	public List<Cliente> getClientes() throws Exception{
		ClienteDao dao = Factories.persistence.createClienteDao();
		return dao.getClientes();
	}
	
	public void update(Cliente c) throws EntityNotFoundException{
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.update(c);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("Cliente no actualizado " + c, e);
		}
	}
	
	
	
}
