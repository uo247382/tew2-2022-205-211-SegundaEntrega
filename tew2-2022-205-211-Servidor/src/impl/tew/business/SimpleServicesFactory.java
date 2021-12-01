package impl.tew.business;


import com.tew.business.PisosService;
import com.tew.business.AgenteService;
import com.tew.business.ClienteService;
import com.tew.business.LoginService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {
	@Override
	public PisosService createPisosService() {
		return new SimplePisosService();
	}

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public ClienteService createClienteService() {
		return new SimpleClienteService();
	}

	@Override
	public PisosParaVisitarService createPisosParaVisitarService() {
		return new SimplePisosParaVisitarService();
	}

	@Override
	public AgenteService createAgenteService() {
		// TODO Auto-generated method stub
		return new SimpleAgenteService();
	}
}
