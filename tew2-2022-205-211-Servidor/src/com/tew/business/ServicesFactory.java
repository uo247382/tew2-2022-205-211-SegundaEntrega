package com.tew.business;

public interface ServicesFactory {
	AgenteService createAgenteService();
	PisosService createPisosService();
	LoginService createLoginService();
	ClienteService createClienteService();
	PisosParaVisitarService createPisosParaVisitarService();
}
