package ec.edu.ups.backendapirest.dao;

import ec.edu.ups.backendapirest.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {



}
