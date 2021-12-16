package impl.tew.business;

import java.util.List;

import com.tew.business.AgenteService;
import com.tew.business.ClienteService;
import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.User;

public class SimpleLoginService implements LoginService {

	@Override
	public User verify(String login, String password) {
		// TODO Auto-generated method stub
		ClienteService service = Factories.services.createClienteService();

		if(!validLogin(login, password)) return null;
		if(validLogin(login, password)) {
			try {
				List<Cliente> lista = service.getClientes();
				for(Cliente c: lista) {
					if((c.getLogin().equals(login)) && (c.getPasswd().equals(password))) {
						return new User(login, "UsuarioCliente");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return new User(login, "UsuarioAgente");
	}


	@Override
	public boolean validLogin(String login, String password) {
		// TODO Auto-generated method stub
		ClienteService serviceCliente = Factories.services.createClienteService();
		AgenteService serviceAgente = Factories.services.createAgenteService();
		String usuario=""; String contra="";
		try {
			List<Cliente> listaClientes = serviceCliente.getClientes();
			List<Agente> listaAgentes = serviceAgente.getAgentes();
			for(Cliente c: listaClientes) {
				if((c.getLogin().equals(login)) && (c.getPasswd().equals(password))){
					usuario = c.getLogin().toString();
					contra = c.getPasswd().toString();	
				}
			}
			for(Agente a: listaAgentes) {
				if((a.getLogin().equals(login)) && (a.getPasswd().equals(password))) {
					usuario = a.getLogin().toString();
					contra = a.getPasswd().toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario.equals(login) && contra.equals(password);
	}



}
