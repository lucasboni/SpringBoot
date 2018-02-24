package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.Categoria;
import br.com.lucas.boni.bittencourt.cursomc.domain.Cidade;
import br.com.lucas.boni.bittencourt.cursomc.domain.Cliente;
import br.com.lucas.boni.bittencourt.cursomc.domain.Endereco;
import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.TipoCliente;
import br.com.lucas.boni.bittencourt.cursomc.dto.ClienteDTO;
import br.com.lucas.boni.bittencourt.cursomc.dto.ClienteNewDTO;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.CidadeRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ClienteRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.EnderecoRepository;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.DataIntegrityException;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find(Integer id) {
        Cliente obj = repo.findOne(id);   //por algum motivo o indone n funciona
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + " tipo " + Categoria.class.getName());
        }
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir por que há entidades relacionadas");
        }
    }

    public List<Cliente> findAll() {
        List<Cliente> list = repo.findAll();
        return list;
    }

    public Page<Cliente> findPage(Integer page, Integer linerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDT0(ClienteDTO obj) {
        return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null,null);
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.save(obj.getEnderecos());
        return obj;
    }

    public Cliente fromDT0(ClienteNewDTO obj) {
        Cliente cli = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), TipoCliente.toEnum(obj.getTipoCliente()),pe.encode(obj.getSenha()));
        Cidade cid = cidadeRepository.findOne(obj.getCidadeId());
        Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(obj.getTelefone1());
        if (obj.getTelefone2() != null) {
            cli.getTelefones().add(obj.getTelefone2());
        }
        if (obj.getTelefone3() != null) {
            cli.getTelefones().add(obj.getTelefone3());
        }
        return cli;
    }
}
