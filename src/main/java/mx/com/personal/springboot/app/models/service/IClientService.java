package mx.com.personal.springboot.app.models.service;

import java.util.List;

import mx.com.personal.springboot.app.models.entity.Cliente;

public interface IClientService {
	List<Cliente> findAll();
	void save(Cliente cliente);
	Cliente findOne(Long id);
	void delete(Long id);
}
