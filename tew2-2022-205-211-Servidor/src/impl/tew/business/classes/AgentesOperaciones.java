package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class AgentesOperaciones {

	public void save(Agente agente) throws EntityAlreadyExistsException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.save(agente);
		} catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Agente ya existe " + agente, ex);
		}
	}

	public void delete(Long id) throws EntityNotFoundException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.delete(id);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Agente no existe" + id, ex);
		}
	}
	
	public Agente find(Long id) throws EntityNotFoundException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		Agente agente = dao.findById(id);
		if (agente == null) {
			throw new EntityNotFoundException("No se ha encontrado el alumno");
		}
		return agente;
	}
	
	public List<Agente> getAgentes() throws Exception {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		return dao.getAgentes();
	}
	
	public void update(Agente agente) throws EntityNotFoundException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.update(agente);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Agente no actualizado" + agente, ex);
		}
	}
}
