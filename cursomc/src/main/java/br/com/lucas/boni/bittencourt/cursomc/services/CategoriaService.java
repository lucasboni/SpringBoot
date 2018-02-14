package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.Categoria;
import br.com.lucas.boni.bittencourt.cursomc.dto.CategoriaDTO;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.CategoriaRepository;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.DataIntegrityException;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Categoria obj = repo.findOne(id);   //por algum motivo o indone n funciona
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + " tipo " + Categoria.class.getName());
        }
        return obj;
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }


    public Categoria update(Categoria obj) {
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui pro");
        }
    }

    public List<Categoria> findAll() {
        List<Categoria> list = repo.findAll();
        return list;
    }

    public Page<Categoria> findPage(Integer page, Integer linerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDT0(CategoriaDTO obj) {
        return new Categoria(obj.getId(), obj.getNome());
    }
}
