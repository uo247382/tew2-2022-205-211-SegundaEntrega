package impl.tew.persistence;


import com.tew.persistence.PisoDao;
import com.tew.persistence.PisosParaVisitarDao;
import com.tew.persistence.ReiniciarDataBaseDao;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.PersistenceFactory;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public PisoDao createPisoDao() {
		// TODO Auto-generated method stub
		return new PisoJdbcDao();
	}

	@Override
	public ClienteDao createClienteDao() {
		// TODO Auto-generated method stub
		return new ClienteJdbcDao();
	}

	@Override
	public PisosParaVisitarDao createPisoParaVisitarDao() {
		// TODO Auto-generated method stub
		return new PisosParaVisitarJdbcDao();
	}

	@Override
	public AgenteDao createAgenteDao() {
		// TODO Auto-generated method stub
		return new AgenteJdbcDao();
	}

	@Override
	public ReiniciarDataBaseDao reiniciar() {
		// TODO Auto-generated method stub
		return new ReiniciarDataBaseJdbcDao();
	}

}
