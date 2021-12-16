package impl.tew.persistence;

import com.tew.model.Agente;
import com.tew.model.Piso;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class PruebasPisosDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PERSISTENCIA DAO ----- PISOS -------");
		PisoDao piso = new PisoJdbcDao();
		System.out.println(piso.getPisos().get(0).getId());
		
		Piso a = new Piso();
		/*a.setIdAgente(3L);
		a.setPrecio(15030583);
		a.setCiudad("MADRID");
		a.setDireccion("AVENIDA MANZANARES ATLETI");
		a.setEstado(3);
		try {
			piso.save(a);
			System.out.println(piso.getPisos().get(5).getCiudad());
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*a = piso.getPisos().get(5);
		a.setCiudad("BARCELONA");
		try {
			piso.update(a);
			System.out.println(piso.getPisos().get(5).getCiudad());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			piso.delete(a.getId());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}

}
