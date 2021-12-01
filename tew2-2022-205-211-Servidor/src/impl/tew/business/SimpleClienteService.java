package impl.tew.business;

import java.util.List;

import com.tew.business.ClienteService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cliente;

import impl.tew.business.classes.ClientesOperaciones;

public class SimpleClienteService implements ClienteService {

	@Override
	public List<Cliente> getClientes() throws Exception {
		return new ClientesOperaciones().getClientes();
	}

	@Override
	public Cliente findById(Long id) throws EntityNotFoundException {
		return new ClientesOperaciones().find(id);
	}

	@Override
	public void saveCliente(Cliente cliente) throws EntityAlreadyExistsException {
		new ClientesOperaciones().save(cliente);
	}

	@Override
	public void updateCliente(Cliente cliente) throws EntityNotFoundException {
		new ClientesOperaciones().update(cliente);
	}

	@Override
	public void deleteCliente(Long id) throws EntityNotFoundException {
		new ClientesOperaciones().delete(id);
	}
}
