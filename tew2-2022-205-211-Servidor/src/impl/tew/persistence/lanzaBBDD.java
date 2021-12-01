package impl.tew.persistence;

import java.util.ArrayList;
import java.util.List;

import com.tew.model.*;
public class lanzaBBDD {

	public static void main(String[] args) {

		PisoJdbcDao prueba = new PisoJdbcDao();
		List<Piso> pisos = new ArrayList<Piso>();
		pisos = prueba.getPisos();		
		
		PisosParaVisitarJdbcDao pruebaPisosParaVisitar = new PisosParaVisitarJdbcDao();
		List<PisoParaVisitar> pisosVisita = new ArrayList<PisoParaVisitar>();
		pisosVisita = pruebaPisosParaVisitar.getPisosParaVisitar();

		ClienteJdbcDao pruebaCliente = new ClienteJdbcDao();
		List<Cliente> cliente = new ArrayList<Cliente>();
		cliente = pruebaCliente.getClientes();		
		
		System.out.println("Hola acabe");
	}
} 
