package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.business.exception.*;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.*;
import com.tew.model.Piso;

public class PisosOperaciones {


	public List<Piso> getPisos() throws Exception{
		PisoDao dao = Factories.persistence.createPisoDao();
		return  dao.getPisos();
	}


	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.save(piso);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Piso ya existe " + piso, ex);
		}
	}


	public void updatePiso(Piso piso) throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.updatePiso(piso);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso no eliminado " + piso, ex);
		}
	}


	public void deletePiso(Long id) throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.delete(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso no eliminado " + id, ex);
		}
	}


	public Piso findById(Long id) throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		Piso a = dao.findById(id);
		if  (a == null) {
			throw new EntityNotFoundException("No se ha encontrado el piso");
		}

		return a;
	}



	public List<Piso> getPisosAgente(Long idAgente) throws Exception{
		PisoDao dao = Factories.persistence.createPisoDao();
		return  dao.getPisosAgente(idAgente);
	}

	public List<Piso> getPisosCliente(Long idCliente) throws Exception{
		PisoDao dao = Factories.persistence.createPisoDao();
		return  dao.getPisosCliente(idCliente);
	}

	public List<Piso> getPisosClienteCiudad(String Ciudad, Long idCliente) throws Exception{
		PisoDao dao = Factories.persistence.createPisoDao();
		return  dao.getPisosClienteCiudad(Ciudad, idCliente);
	}

	public List<Piso> getPisosClientePrecio(Double minPrecio, Double maxPrecio, Long idCliente) throws Exception{
		PisoDao dao = Factories.persistence.createPisoDao();
		return  dao.getPisosClientePrecio(minPrecio, maxPrecio, idCliente);
	}


	public void savePisoAgente(Long idAgente, Piso piso) throws EntityAlreadyExistsException {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.savePisoAgente(idAgente, piso);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Piso ya existe " + idAgente + " , " + piso, ex);
		}
	}

	public void duplicarPiso(Piso piso) throws Exception{
		PisoDao pisodao = Factories.persistence.createPisoDao();
		try {
			pisodao.duplicarPiso(piso);
		}catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso no duplicado " + piso, ex);
		}
	}
}
