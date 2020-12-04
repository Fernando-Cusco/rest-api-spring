package ec.edu.ups.backendapirest.services;

import ec.edu.ups.backendapirest.models.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();

    public Cliente save(Cliente cliente);

    public void delete(Long id);

    public Cliente findById(Long id);
}
