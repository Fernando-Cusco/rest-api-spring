package ec.edu.ups.backendapirest.services;

import ec.edu.ups.backendapirest.dao.IClienteDao;
import ec.edu.ups.backendapirest.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteDao clienteDao;

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Transactional(readOnly = false)
    public Cliente save(Cliente cliente) {
        clienteDao.save(cliente);
        return null;
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDao.findById(id).orElse(null);
    }


}
