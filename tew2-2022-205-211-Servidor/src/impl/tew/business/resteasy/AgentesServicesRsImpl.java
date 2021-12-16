package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.resteasy.AgentesServicesRs;
import com.tew.model.Agente;

import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

import impl.tew.business.classes.AgentesOperaciones;


public class AgentesServicesRsImpl implements AgentesServicesRs{

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
