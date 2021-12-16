package com.tew.business;

import java.util.List;

import com.tew.model.Agente;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AgenteService {
	
	void save(Agente p) throws AlreadyPersistedException;
	void update(Agente p) throws NotPersistedException;
	void delete(Long id) throws NotPersistedException;
	void reinicioBaseDatos() throws Exception ;
	List<Agente> getAgentes() throws Exception ;
}
