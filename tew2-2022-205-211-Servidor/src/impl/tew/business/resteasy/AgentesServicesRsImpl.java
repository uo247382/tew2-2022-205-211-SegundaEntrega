package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AgentesServicesRs;
import com.tew.model.Agente;

import impl.tew.business.classes.AgentesOperaciones;

public class AgentesServicesRsImpl implements AgentesServicesRs {

	@Override
	public Agente findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAgente(Agente cliente) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAgente(Agente cliente) throws EntityNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAgente(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reinicioBaseDatos() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Agente> getAgentes() {
		// TODO Auto-generated method stub
		try {
			return new AgentesOperaciones().getAgentes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Listado de Agentes IMPL");
			return null;
		}
	}

}
