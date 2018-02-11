package br.com.lucas.boni.bittencourt.cursomc.domain;

import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.EstadoPagamento;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class PagamentoComCartao extends Pagamento implements Serializable {
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
