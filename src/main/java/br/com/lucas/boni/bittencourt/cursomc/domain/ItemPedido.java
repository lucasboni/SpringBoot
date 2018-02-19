package br.com.lucas.boni.bittencourt.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
public class ItemPedido implements Serializable {

    @JsonIgnore
    @EmbeddedId                                  //usa essa anotacao para chave composta
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedido() {
    }

    public void setPedido(Pedido pedido)
    {
        this.id.setPedido(pedido);
    }

    public void setPorduto(Produto produto){
        this.id.setProduto(produto);
    }

    public Double getSubTotal(){
        return (preco - desconto)* quantidade;
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @JsonIgnore //acessa diretamente logo sao serializados          ->removido pois quero este campo
    public Pedido getPedido() {
        return id.getPedido();
    }

    //@JsonIgnore //acessa diretamente logo sao serializados
    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));//fala que quer usar a moeda local
        final StringBuffer sb = new StringBuffer();
        sb.append(getProduto().getNome());
        sb.append(", Qte: ");
        sb.append(getQuantidade());
        sb.append(", Preco Unitario: ");
        sb.append(nf.format(getPreco()));
        sb.append(", Subtotal: ");
        sb.append(nf.format(getSubTotal()));
        sb.append("\r\n");


        return sb.toString();
    }
}
