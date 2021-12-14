package com.tew.business.resteasy;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.AgenteService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;
import com.tew.model.Piso;

@Path("/AgentesServicesRs")
public interface AgentesServicesRs extends AgenteService{
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Agente> getAgentes();
	




}
