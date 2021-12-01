package com.tew.persistence;

import com.tew.persistence.PisoDao;

public interface PersistenceFactory {
	AgenteDao createAgenteDao();
	PisoDao createPisoDao();
	ClienteDao createClienteDao();
	PisosParaVisitarDao createPisoParaVisitarDao();
}

