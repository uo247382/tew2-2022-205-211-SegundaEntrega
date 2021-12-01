package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.PisoParaVisitar;

public interface PisosParaVisitarService {
	List<PisoParaVisitar> getPisosParaVisitar() throws Exception;
	PisoParaVisitar findById(Long idp, Long idc) throws EntityNotFoundException;
	void savePisoParaVisitar(PisoParaVisitar p) throws EntityAlreadyExistsException;
	void updatePisoParaVisitar2(PisoParaVisitar p) throws EntityNotFoundException;
	void updatePisoParaVisitar3(PisoParaVisitar p) throws EntityNotFoundException;
	void deletePisoParaVisitar(Long idp, Long idc) throws EntityNotFoundException;
	List<PisoParaVisitar> getPisosParaVisitarAgente() throws Exception;
}