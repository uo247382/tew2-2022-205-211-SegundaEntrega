package com.tew.persistence;

import java.util.List;

import com.tew.model.PisoParaVisitar;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface PisosParaVisitarDao {
	List<PisoParaVisitar> getPisosParaVisitar();
	List<PisoParaVisitar> getPisosParaVisitarAgente();
	void save(PisoParaVisitar pisoParaVisitar) throws AlreadyPersistedException;
	void update2(PisoParaVisitar pisoParaVisitar) throws NotPersistedException;
	void update3(PisoParaVisitar pisoParaVisitar) throws NotPersistedException;
	void delete(Long id, Long idCliente) throws NotPersistedException;
	PisoParaVisitar findById(Long id, Long iDCliente);
}