package impl.tew.business;

import java.util.List;

import com.tew.business.PisoParaVisitarService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.PisoParaVisitar;

import impl.tew.business.classes.PisosParaVisitarOperaciones;

public class SimplePisoParaVisitarService implements PisoParaVisitarService{

	@Override
	public List<PisoParaVisitar> getPisosParaVisitar() throws Exception {
		// TODO Auto-generated method stub
		return new PisosParaVisitarOperaciones().getPisosParaVisitar();
	}

	@Override
	public PisoParaVisitar findByIds(Long idp, Long idc) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return new PisosParaVisitarOperaciones().find(idp, idc);
	}

	@Override
	public void savePisoParaVisitar(PisoParaVisitar pv) throws EntityAlreadyExistsException {
		
		 new PisosParaVisitarOperaciones().save(pv);
		
	}

	




	@Override
	public void updatePisoParaVisitar2(PisoParaVisitar p) throws EntityNotFoundException {
		
		new PisosParaVisitarOperaciones().update2(p);
		
	}
	@Override
	public void updatePisoParaVisitar3(PisoParaVisitar p) throws EntityNotFoundException {
		
		new PisosParaVisitarOperaciones().update3(p);
		
	}

	@Override
	public void deletePisoParaVisitar(Long idp, Long idc) throws EntityNotFoundException {
		
		new PisosParaVisitarOperaciones().delete(idp,idc);
	}

}
