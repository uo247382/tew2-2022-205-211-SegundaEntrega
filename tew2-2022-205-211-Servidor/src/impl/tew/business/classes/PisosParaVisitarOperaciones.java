package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisosParaVisitarDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class PisosParaVisitarOperaciones {
	public void save(PisoParaVisitar pisoParaVisitar) throws EntityAlreadyExistsException {
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.save(pisoParaVisitar);
		} catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("PisoParaVisitar ya existe " + pisoParaVisitar, ex);
		}
	}

	public void delete(Long iDPiso, Long iDCliente) throws EntityNotFoundException {
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.delete(iDPiso, iDCliente);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("PisoParaVisitar no existe" + iDPiso, ex);
		}
	}
	
	public PisoParaVisitar find(Long iDPiso, Long iDCliente) throws EntityNotFoundException {
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		PisoParaVisitar pisoParaVisitar = dao.findById(iDPiso, iDCliente);
		if (pisoParaVisitar == null) {
			throw new EntityNotFoundException("No se ha encontrado el PisoParaVisitar");
		}
		return pisoParaVisitar;
	}
	
	public List<PisoParaVisitar> getPisoParaVisitar() throws Exception {
		System.out.println("ANTESSSSSS mal PisosParaVisitarOperaciones");

		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		System.out.println("DESPUESSSSSS mal    PisosParaVisitarOperaciones");

		return dao.getPisosParaVisitar();
	}
	
	public List<PisoParaVisitar> getPisoParaVisitarAgente() throws Exception {
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeste pisosparavisitarOperaciones");

		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		System.out.println("DESPUESSSSSS    PisosParaVisitarOperaciones");

		return dao.getPisosParaVisitarAgente();
	}
	
	public void update2(PisoParaVisitar pisoParaVisitar) throws EntityNotFoundException {
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.update2(pisoParaVisitar);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("PisoParaVisitar no actualizado" + pisoParaVisitar, ex);
		}
	}
	
	public void update3(PisoParaVisitar pisoParaVisitar) throws EntityNotFoundException {
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.update3(pisoParaVisitar);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("PisoParaVisitar no actualizado" + pisoParaVisitar, ex);
		}
	}
}
