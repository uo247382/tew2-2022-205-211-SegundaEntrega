package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.PisosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimplePisosService implements PisosService {

	@Override
	public List<Piso> getPisos() throws Exception{
		return new PisosListado().getPisos();
	}

	@Override
	public void savePiso(Piso p) throws EntityAlreadyExistsException {
		new PisosAlta().save(p);
	}

	@Override
	public void updatePiso(Piso p) throws EntityNotFoundException {
		new PisosUpdate().update(p);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {
		new PisosBaja().delete(id);
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
	}

}
