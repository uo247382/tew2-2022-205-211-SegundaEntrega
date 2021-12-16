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
	public void save(PisoParaVisitar pv) throws EntityAlreadyExistsException{
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.save(pv);
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityAlreadyExistsException("Cliente ya existe " + pv, e);
		}
	}
	
	public void delete(Long idp, Long idc) throws EntityNotFoundException{
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.delete(idp, idc);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("Piso para visitar no existe " + idp, e);
		}
	}
	
	public PisoParaVisitar find(Long idp, Long idc) throws EntityNotFoundException{
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		PisoParaVisitar a = dao.findById(idp, idc);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el Piso para visitar");
		}
		
		return a;
	}
	
	public List<PisoParaVisitar> getPisosParaVisitar() throws Exception{
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		return dao.getPisosParaVisitar();
	}
	
	public void update2(PisoParaVisitar c) throws EntityNotFoundException{
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.update2(c);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("PisoParaVisitar no actualizado " + c, e);
		}
	}
	public void update3(PisoParaVisitar c) throws EntityNotFoundException{
		PisosParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.update2(c);
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			throw new EntityNotFoundException("PisoParaVisitar no actualizado " + c, e);
		}
	}
}
