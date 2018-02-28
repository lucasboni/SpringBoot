package br.com.lucas.boni.bittencourt.cursomc.domain.enuns;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private final Integer id;
    private final String descricao;

    private Perfil(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (Perfil x : Perfil.values()) {
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
