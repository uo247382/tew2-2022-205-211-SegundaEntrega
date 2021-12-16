package com.tew.persistence;

import java.util.List;

import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface PisosParaVisitarDao {
	List<PisoParaVisitar> getPisosParaVisitar();
	void save(PisoParaVisitar p) throws AlreadyPersistedException;
	void update3(PisoParaVisitar p) throws NotPersistedException;
	void delete(Long id, Long idc) throws NotPersistedException;
	PisoParaVisitar findById(Long id, Long idc);
	void update2(PisoParaVisitar p) throws NotPersistedException;
	
}
