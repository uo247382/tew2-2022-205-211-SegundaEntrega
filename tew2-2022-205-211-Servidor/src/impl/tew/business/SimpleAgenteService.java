package impl.tew.business;

import java.util.List;

import com.tew.business.AgenteService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;

import impl.tew.business.classes.AgentesOperaciones;

public class SimpleAgenteService implements AgenteService {

	@Override
	public List<Agente> getAgentes() throws Exception {
		return new AgentesOperaciones().getAgentes();
	}

	@Override
	public Agente findById(Long id) throws EntityNotFoundException {
		return new AgentesOperaciones().find(id);
	}

	@Override
	public void saveAgente(Agente agente) throws EntityAlreadyExistsException {
		new AgentesOperaciones().save(agente);
	}

	@Override
	public void updateAgente(Agente agente) throws EntityNotFoundException {
		new AgentesOperaciones().update(agente);
	}

	@Override
	public void deleteAgente(Long id) throws EntityNotFoundException {
		new AgentesOperaciones().delete(id);
	}

	@Override
	public void reinicioBaseDatos() throws Exception {
		// TODO Auto-generated method stub
		
	}


}
