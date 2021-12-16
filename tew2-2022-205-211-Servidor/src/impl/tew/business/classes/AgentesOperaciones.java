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
	public void save(Agente c) throws EntityAlreadyExistsException{
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.save(c);
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityAlreadyExistsException("Agente ya existe " + c, e);
		}
	}
	
	public void delete(Long id) throws EntityNotFoundException{
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.delete(id);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("Agente no existe " + id, e);
		}
	}
	
	public Agente find(Long id) throws EntityNotFoundException{
		AgenteDao dao = Factories.persistence.createAgenteDao();
		Agente a = dao.findById(id);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el alumno");
		}
		
		return a;
	}
	
	public List<Agente> getAgentes() throws Exception{
		AgenteDao dao = Factories.persistence.createAgenteDao();
		return dao.getAgentes();
	}
	
	public void update(Agente c) throws EntityNotFoundException{
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.update(c);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("Agente no actualizado " + c, e);
		}
	}

	public void reinicioBaseDatos() throws Exception {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.reset();
		} catch (Exception e) {
			throw new Exception("Excepcion a la hora de reiniciar la base de datos " + e);
		}
	}

}
