package impl.tew.business;

import java.util.List;

import com.tew.business.PisosParaVisitarService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.PisoParaVisitar;

import impl.tew.business.classes.PisosParaVisitarOperaciones;

public class SimplePisosParaVisitarService implements PisosParaVisitarService {

	@Override
	public List<PisoParaVisitar> getPisosParaVisitar() throws Exception {
		System.out.println("LLAMO AL METODO GETPISOSPARAVISITAR DE LA CLASE SIMPLEPISOSPARAVISITARSERVICE");
		return new PisosParaVisitarOperaciones().getPisoParaVisitar();
	}

	@Override
	public List<PisoParaVisitar> getPisosParaVisitarAgente() throws Exception {
		System.out.println("LLAMO AL METODO GETPISOSPARAVISITARAAAAAAAAAAAAAAAAAAAAAAAAAAAGEEEEEEEENTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE DE LA CLASE SIMPLEPISOSPARAVISITARSERVICE");
		return new PisosParaVisitarOperaciones().getPisoParaVisitarAgente();
	}

	@Override
	public PisoParaVisitar findById(Long iDPiso, Long iDCliente) throws EntityNotFoundException {
		return new PisosParaVisitarOperaciones().find(iDPiso, iDCliente);
	}

	@Override
	public void savePisoParaVisitar(PisoParaVisitar pisoParaVisitar) throws EntityAlreadyExistsException {
		new PisosParaVisitarOperaciones().save(pisoParaVisitar);
	}

	@Override
	public void updatePisoParaVisitar2(PisoParaVisitar pisoParaVisitar) throws EntityNotFoundException {
		new PisosParaVisitarOperaciones().update2(pisoParaVisitar);
	}

	@Override
	public void updatePisoParaVisitar3(PisoParaVisitar pisoParaVisitar) throws EntityNotFoundException {
		new PisosParaVisitarOperaciones().update3(pisoParaVisitar);
	}

	@Override
	public void deletePisoParaVisitar(Long iDPiso,Long iDCliente) throws EntityNotFoundException {
		new PisosParaVisitarOperaciones().delete(iDPiso, iDCliente);
	}
}
