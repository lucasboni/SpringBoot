package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.Categoria;
import br.com.lucas.boni.bittencourt.cursomc.domain.Cliente;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ClienteRepository;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Integer id) {
        Cliente obj = repo.findOne(id);   //por algum motivo o indone n funciona
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + " tipo " + Categoria.class.getName());
        }
        return obj;
    }
}
