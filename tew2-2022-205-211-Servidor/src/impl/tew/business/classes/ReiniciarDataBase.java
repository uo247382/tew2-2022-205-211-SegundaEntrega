package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.persistence.ReiniciarDataBaseDao;

public class ReiniciarDataBase {

	public void reiniciar() {
		ReiniciarDataBaseDao dao = Factories.persistence.reiniciar();
		dao.reinicia();
	}
}
