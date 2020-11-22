package br.com.lucas.boni.bittencourt.cursomc.domain.enuns;

public enum TipoCliente {
    PESSOAFISICA(1, "Pessoa Fisica"),
    PESSOAJURIDICA(2, "Pessoa Juridica");

    private final Integer id;
    private final String descricao;

    private TipoCliente(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (TipoCliente x : TipoCliente.values()) {
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
