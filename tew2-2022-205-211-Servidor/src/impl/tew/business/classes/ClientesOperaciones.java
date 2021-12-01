package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class ClientesOperaciones {

	public void save(Cliente cliente) throws EntityAlreadyExistsException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.save(cliente);
		} catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Cliente ya existe " + cliente, ex);
		}
	}

	public void delete(Long id) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.delete(id);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Cliente no existe" + id, ex);
		}
	}
	
	public Cliente find(Long id) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		Cliente cliente = dao.findById(id);
		if (cliente == null) {
			throw new EntityNotFoundException("No se ha encontrado el alumno");
		}
		return cliente;
	}
	
	public List<Cliente> getClientes() throws Exception {
		ClienteDao dao = Factories.persistence.createClienteDao();
		return dao.getClientes();
	}
	
	public void update(Cliente cliente) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.update(cliente);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Cliente no actualizado" + cliente, ex);
		}
	}
}
