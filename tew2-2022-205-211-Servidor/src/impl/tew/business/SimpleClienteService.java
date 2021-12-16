package impl.tew.business;

import java.util.List;

import com.tew.business.ClienteService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cliente;
import com.tew.model.Piso;

import impl.tew.business.classes.ClientesOperaciones;
import impl.tew.business.classes.PisosBuscar;
import impl.tew.business.classes.PisosListado;

public class SimpleClienteService implements ClienteService{

	@Override
	public List<Cliente> getClientes() throws Exception {
		// TODO Auto-generated method stub
		return new ClientesOperaciones().getClientes();
	}

	@Override
	public Cliente findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return new ClientesOperaciones().find(id);
	}

	@Override
	public void saveCliente(Cliente c) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		new ClientesOperaciones().save(c);
	}

	@Override
	public void updateCliente(Cliente c) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new ClientesOperaciones().update(c);
	}

	@Override
	public void deleteCliente(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new ClientesOperaciones().delete(id);
	}



}
