package br.com.lucas.boni.bittencourt.cursomc.resources;

import br.com.lucas.boni.bittencourt.cursomc.domain.Cliente;
import br.com.lucas.boni.bittencourt.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente buscar = service.buscar(id);
        return ResponseEntity.ok().body(buscar);
    }

}
