package br.com.lucas.boni.bittencourt.cursomc.domain.enuns;

public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private final Integer id;
    private final String descricao;

    private EstadoPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (x.getId().equals(cod)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
