package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.PisosServicesRs;
import com.tew.model.Piso;

import impl.tew.business.classes.PisosAlta;
import impl.tew.business.classes.PisosBaja;
import impl.tew.business.classes.PisosBuscar;
import impl.tew.business.classes.PisosListado;
import impl.tew.business.classes.PisosUpdate;
import impl.tew.business.classes.ReiniciarDataBase;

public class PisosServicesRsImpl implements PisosServicesRs {

	@Override
	public List<Piso> getPisos() {
		// TODO Auto-generated method stub
		try {
			return new PisosListado().getPisos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Listado de pisos IMPL");
			return null;
		}
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return new PisosBuscar().find(id);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new PisosBaja().delete(id);
	}

	@Override
	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		new PisosAlta().save(piso);
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new PisosUpdate().update(piso);
	}

	
	public void borrarDataBase() {
		// TODO Auto-generated method stub
		new ReiniciarDataBase().reiniciar();
	}

}
