package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.Pedido;
import br.com.lucas.boni.bittencourt.cursomc.domain.Produto;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.PedidoRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ProdutoRepository;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Produto buscar(Integer id) {
        Produto obj = repo.findOne(id);   //por algum motivo o indone n funciona
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + " tipo " + Pedido.class.getName());
        }
        return obj;
    }

    public Page<Produto> search(String nome, List<Produto> ids,Integer page, Integer linerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linerPage, Sort.Direction.valueOf(direction), orderBy);

    }
}
