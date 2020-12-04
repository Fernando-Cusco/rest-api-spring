package ec.edu.ups.backendapirest.controllers;


import ec.edu.ups.backendapirest.models.Cliente;
import ec.edu.ups.backendapirest.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error, al consultar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(cliente == null) {
            response.put("mensaje", "El cliente no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping(value = "/clientes")
    public ResponseEntity<?> nuevoCliente(@RequestBody Cliente cliente) {
        Cliente nuevo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            nuevo = clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error, al crear el nuevo cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente, "+cliente.getNombres()+" creado con éxito");
        response.put("cliente", cliente);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/clientes/{id}")
    public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
        Cliente c = clienteService.findById(id);
        Cliente update = null;
        Map<String, Object> response = new HashMap<>();
        if(c == null) {
            response.put("mensaje", "El cliente no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            c.setApellidos(cliente.getApellidos());
            c.setNombres(cliente.getNombres());
            c.setEmail(cliente.getEmail());
            update = clienteService.save(c);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error, al actualizar el cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente, "+cliente.getNombres()+" se actualizó con éxito");
        response.put("cliente", cliente);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error, al eliminar al cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente se eliminó con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


}
