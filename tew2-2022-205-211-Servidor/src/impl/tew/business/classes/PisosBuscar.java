package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.PisoDao;

public class PisosBuscar {
	public Piso find(Long id) throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		Piso piso = dao.findById(id);
		if ( piso == null) {
			throw new EntityNotFoundException("No se ha encontrado el piso");
		}
		return piso;
	}
}
