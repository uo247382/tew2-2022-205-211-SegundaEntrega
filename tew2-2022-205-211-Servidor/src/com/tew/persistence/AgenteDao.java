package com.tew.persistence;

import java.util.List;

import com.tew.model.Agente;
import com.tew.model.Piso;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AgenteDao {

	List<Agente> getAgentes();
	void save(Agente a) throws AlreadyPersistedException;
	void update(Agente a) throws NotPersistedException;
	void delete(Long id) throws NotPersistedException;
	Agente findById(Long id);
	void reset() throws Exception;
	
}
