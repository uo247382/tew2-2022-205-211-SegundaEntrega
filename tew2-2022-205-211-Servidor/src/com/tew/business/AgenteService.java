package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;

public interface AgenteService {
	List<Agente> getAgentes() throws Exception;
	Agente findById(Long id) throws EntityNotFoundException;
	void saveAgente(Agente cliente) throws EntityAlreadyExistsException;
	void updateAgente(Agente cliente) throws EntityNotFoundException;
	void reinicioBaseDatos() throws Exception ;
	void deleteAgente(Long id) throws EntityNotFoundException;
}
