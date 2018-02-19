package br.com.lucas.boni.bittencourt.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date instante;

    //@JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
//para nao da erro de entidade transitne quando vai salvarum pagamento e o pedido dele
    private Pagamento pagamento;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {//como o pagamento é interdepedente nao se pode enviar ele no contrutor
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Double getTotal() {
        Double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma += ip.getSubTotal();
        }
        return soma;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return id != null ? id.equals(pedido.id) : pedido.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));//fala que quer usar a moeda local
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuffer sb = new StringBuffer("Pedido número: ");
        sb.append(id);
        sb.append(", Instante: ");
        sb.append(sdf.format(getInstante()));
        sb.append(", Cliente: ");
        sb.append(getCliente().getNome());
        sb.append(", Situacao pagamento: ");
        sb.append(getPagamento().getEstado().getDescricao());
        sb.append("\nDetalhes\n");
        for (ItemPedido itemPedido:getItens()) {
            sb.append(itemPedido.toString());
        }
        sb.append(", Valor total: ");
        sb.append(nf.format(getTotal()));
        return sb.toString();
    }
}
