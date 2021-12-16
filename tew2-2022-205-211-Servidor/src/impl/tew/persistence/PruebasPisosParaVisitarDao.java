package impl.tew.persistence;

import com.tew.model.Cliente;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisosParaVisitarDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class PruebasPisosParaVisitarDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PisosParaVisitarDao piso = new PisosParaVisitarJdbcDao();
		System.out.println(piso.getPisosParaVisitar().get(0).getC());
		
		PisoParaVisitar a = new PisoParaVisitar();
		/*a.setP(5L);
		a.setC(4L);
		a.setFechaHoraCita(12838312);
		a.setEstado(3);
		try {
			piso.save(a);
			System.out.println(piso.getPisosParaVisitar().get(2).getFechaHoraCita());
		} catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*a = piso.getPisosParaVisitar().get(2);
		System.out.println(a.getEstado());
		a.setEstado(2);
		try {
			piso.update(a);
			System.out.println(piso.getPisosParaVisitar().get(2).getEstado());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			piso.delete(a.getP(), a.getC());
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
