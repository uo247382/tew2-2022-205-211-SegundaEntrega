package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.PisosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

public class SimplePisosService implements PisosService {
	@Override
	public List<Piso> getPisos() throws Exception {
		return new PisosListado().getPisos();
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
	}

	@Override
	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		new PisosAlta().save(piso);
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		new PisosOperaciones().updatePiso(piso);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {
		new PisosBaja().delete(id);
	}

	@Override
	public List<Piso> getPisosCliente(Long idCliente) throws Exception {
		return new PisosOperaciones().getPisosCliente(idCliente);
	}

	@Override
	public List<Piso> getPisosAgente(Long idAgente) throws Exception {
		return new PisosOperaciones().getPisosAgente(idAgente);
	}

	@Override
	public List<Piso> getPisosClienteCiudad(String Ciudad, Long idCliente) throws Exception {
		return new PisosOperaciones().getPisosClienteCiudad(Ciudad, idCliente);
	}

	@Override
	public void savePisoAgente(Long idAgente, Piso piso) throws EntityAlreadyExistsException {
		new PisosOperaciones().savePisoAgente(idAgente, piso);
	}

	@Override
	public List<Piso> getPisosClientePrecio(Double minPrecio, Double maxPrecio, Long idCliente) throws Exception {
		return new PisosOperaciones().getPisosClientePrecio(minPrecio, maxPrecio, idCliente);
	}

	@Override
	public void duplicarPiso(Piso piso) throws Exception {
		new PisosOperaciones().duplicarPiso(piso);
	}
}
