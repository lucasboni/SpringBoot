package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.Categoria;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.CategoriaRepository;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Categoria categoria = repo.findOne(id);   //por algum motivo o indone n funciona
        if(categoria == null){
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + " tipo " + Categoria.class.getName());
        }
        return categoria;
    }
}
