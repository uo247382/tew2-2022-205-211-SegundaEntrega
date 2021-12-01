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

	public User verify(String login, String password) {

		System.out.println("Entro a verificar");
		System.out.println(login);
		System.out.println(password);
		String tipo="";

		ClienteService service = Factories.services.createClienteService();
		AgenteService serviceA = Factories.services.createAgenteService();

		if (! validLogin(login, password)) return null;
		if (validLogin(login, password)) {
			try {
				List<Cliente> listCliente = service.getClientes();
				for (Cliente cliente:listCliente) {
					if ((cliente.getLogin().equals(login)) && (cliente.getPasswd().equals(password))) {
						System.out.println(cliente.getLogin().equals(login));
						System.out.println(cliente.getPasswd().equals(password));
						tipo="UsuarioCliente";
						return new User(login, tipo);
					}
				}
				List<Agente> listAgente = serviceA.getAgentes();
				for (Agente agente:listAgente) {
					if ((agente.getLogin().equals(login)) && (agente.getPasswd().equals(password))) {
						System.out.println(agente.getLogin().equals(login));
						System.out.println(agente.getPasswd().equals(password));
						tipo="UsuarioAgente";

						return new User(login, tipo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("holaaaaaaaaa");
		System.out.println(tipo);

		return new User(login, tipo);
	}

	public boolean validLogin(String login, String password) {
		ClienteService serviceCliente = Factories.services.createClienteService();
		AgenteService agenteService = Factories.services.createAgenteService();
		String usuario="";
		String passwd="";
		System.out.println("Entro a validar el login");

		try {
			List<Cliente> listClientes = serviceCliente.getClientes();

			for(Cliente cliente: listClientes) {
				System.out.println("usuario: "+cliente.getLogin());
				System.out.println("password: "+cliente.getPasswd());
				if((cliente.getLogin().equals(login)) && (cliente.getPasswd().equals(password))){
					usuario = cliente.getLogin().toString();
					passwd = cliente.getPasswd().toString();	
				}
			}
			List<Agente> listAgentes = agenteService.getAgentes();
			for(Agente agente: listAgentes) {
				System.out.println("usuario: "+agente.getLogin());
				System.out.println("password: "+agente.getPasswd());
				if((agente.getLogin().equals(login)) && (agente.getPasswd().equals(password))){
					usuario = agente.getLogin().toString();
					passwd = agente.getPasswd().toString();	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("he validado el login");
		System.out.println(login);
		System.out.println(password);
		System.out.println(usuario);
		System.out.println(passwd);

		return usuario.equals(login) && passwd.equals(password);
	}
}
