package mx.com.personal.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import mx.com.personal.springboot.app.models.entity.Cliente;

public interface IClientDAO extends CrudRepository<Cliente, Long> {
}
