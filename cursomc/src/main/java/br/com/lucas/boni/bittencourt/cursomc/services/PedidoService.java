package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.ItemPedido;
import br.com.lucas.boni.bittencourt.cursomc.domain.PagamentoComBoleto;
import br.com.lucas.boni.bittencourt.cursomc.domain.Pedido;
import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.EstadoPagamento;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ItemPedidoRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.PagamentoRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.PedidoRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ProdutoRepository;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BoletoService boletoService;


    public Pedido find(Integer id) {
        Pedido obj = repo.findOne(id);   //por algum motivo o indone n funciona
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + " tipo " + Pedido.class.getName());
        }
        return obj;
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preemcherPagamentoComBoleto(pagto, obj.getInstante());
        }

        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido ip : obj.getItens()) {  //inicializa os itensPedidos com valores padrões
            ip.setDesconto(0.0);
            ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);

        }
        itemPedidoRepository.save(obj.getItens());
        return obj;
    }
}
