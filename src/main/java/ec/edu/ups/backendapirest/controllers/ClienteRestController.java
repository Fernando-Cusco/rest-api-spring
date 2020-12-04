package ec.edu.ups.backendapirest.controllers;


import ec.edu.ups.backendapirest.models.Cliente;
import ec.edu.ups.backendapirest.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping(value = "/clientes")
    public List<Cliente> listarClientes() {
        return clienteService.findAll();
    }

    @GetMapping(value = "/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarCliente(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping(value = "/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente nuevoCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping(value = "/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente actualizarCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
        Cliente c = clienteService.findById(id);
        c.setApellidos(cliente.getApellidos());
        c.setNombres(cliente.getNombres());
        c.setEmail(cliente.getEmail());
        return clienteService.save(c);
    }

    @DeleteMapping(value = "/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.delete(id);
    }


}
