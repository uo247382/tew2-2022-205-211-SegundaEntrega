package com.tew.persistence;

import java.util.List;

import com.tew.model.Piso;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Alumno.
 * 
 * En esta versi��n aparecen los otros m��todos b��sicos de un servicio 
 * de persistencia
 * 
 * @author alb
 *
 */
public interface PisoDao {

	List<Piso> getPisos();
	void save(Piso p) throws AlreadyPersistedException;
	void update(Piso p) throws NotPersistedException;
	void delete(Long id) throws NotPersistedException;
	Piso findById(Long id);

}