package com.tew.business;

public interface ServicesFactory {
	
	PisosService createPisosService();
	ClienteService createClienteService();
	AgenteService createAgenteService();
	LoginService createLoginService();
	PisoParaVisitarService createPisosParaVisitarService();
}
