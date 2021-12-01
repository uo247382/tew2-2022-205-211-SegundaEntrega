package com.tew.persistence;

import java.util.List;

import com.tew.model.Piso;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Piso.
 * 
 * En esta versi��n aparecen los otros m��todos b��sicos de un servicio 
 * de persistencia
 * 
 * @author alb
 *
 */
public interface PisoDao {

	List<Piso> getPisos();
	void save(Piso a) throws AlreadyPersistedException;
	void updatePiso(Piso a) throws NotPersistedException;
	void delete(Long id) throws NotPersistedException;
	Piso findById(Long id);
	List<Piso> getPisosAgente(Long idAgente);
	List<Piso> getPisosCliente(Long idCliente);
	List<Piso> getPisosClienteCiudad(String Ciudad, Long idCliente) throws Exception;
	List<Piso> getPisosClientePrecio(Double minPrecio, Double maxPrecio, Long idCliente) throws Exception;
	void savePisoAgente(Long idAgente, Piso piso) throws AlreadyPersistedException;
	void duplicarPiso(Piso piso) throws Exception;
}