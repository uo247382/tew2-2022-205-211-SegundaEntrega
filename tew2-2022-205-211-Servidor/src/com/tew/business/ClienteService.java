package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cliente;

public interface ClienteService {
	List<Cliente> getClientes() throws Exception;
	Cliente findById(Long id) throws EntityNotFoundException;
	void saveCliente(Cliente cliente) throws EntityAlreadyExistsException;
	void updateCliente(Cliente cliente) throws EntityNotFoundException;
	void deleteCliente(Long id) throws EntityNotFoundException;

}