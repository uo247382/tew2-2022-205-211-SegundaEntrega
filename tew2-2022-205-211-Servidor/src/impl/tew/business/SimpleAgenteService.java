package impl.tew.business;

import java.util.List;

import com.tew.business.AgenteService;
import com.tew.model.Agente;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

import impl.tew.business.classes.AgentesOperaciones;

public class SimpleAgenteService implements AgenteService {

	@Override
	public void save(Agente p) throws AlreadyPersistedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Agente p) throws NotPersistedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) throws NotPersistedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reinicioBaseDatos() throws Exception {
		// TODO Auto-generated method stub
		new AgentesOperaciones().reinicioBaseDatos();
	}

	@Override
	public List<Agente> getAgentes() throws Exception {
		// TODO Auto-generated method stub
		return new AgentesOperaciones().getAgentes();
	}

}
