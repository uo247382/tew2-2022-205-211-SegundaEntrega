package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.PisoDao;

/**
 * Esta clase pertenece a la capa de persistencia y ejecuta un proceso 
 * de negocio.
 * 
 * Si el problema a resolver fuese m??????s complejo habr?????? otras muchas clases de 
 * este estilo en esta capa. 
 * 
 * Las clases que forman la capa de negocio pueden necesitar acceder a la capa
 * de persistencia para resolver su cometido. Esta oculta los detalles de la 
 * tecnolog??????a de persistencia ofreciendo m??????todos del estilo: crear, borrer, 
 * actualizar y diversas consultas.
 * 
 */
public class PisosListado {

	public List<Piso> getPisos() throws Exception {
		// Aqu?????? iria l??????gica de negocio que ejecutase alg??????n proceso ...
		//... en este caso el ejemplo estan sencillo que no hay nada que hacer
		
		// Acceso a la capa de persistencia a traves de su fachada
		// La fachada se obtiene de la factor??????a
		PisoDao dao = Factories.persistence.createPisoDao();
		return  dao.getPisos();

		// Aqu?????? podr??????a ir m??????s l??????gica de negocio que procesase los datos traidos 
		// de persistencia
		// ...
	}
}
