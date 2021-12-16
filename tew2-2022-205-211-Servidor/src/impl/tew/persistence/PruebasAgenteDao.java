package impl.tew.persistence;

import com.tew.model.Agente;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class PruebasAgenteDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PERSISTENCIA DAO ----- AGENTE -------");
		AgenteDao agente = new AgenteJdbcDao();
		System.out.println(agente.getAgentes().get(0).getLogin());
		
		Agente a = new Agente();
		/*a.setLogin("pruebas@pruebas.com");
		a.setPasswd("pruebas1");
		try {
			agente.save(a);
			System.out.println(agente.getAgentes().get(2).getLogin());
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a = agente.getAgentes().get(2);
		a.setLogin("pruebas_2@gmail.com");
		try {
			agente.update(a);
			System.out.println(agente.getAgentes().get(2).getLogin());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			agente.delete(a.getId());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
	}

}
