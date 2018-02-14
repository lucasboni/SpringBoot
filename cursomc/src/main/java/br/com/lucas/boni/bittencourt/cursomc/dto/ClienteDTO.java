package br.com.lucas.boni.bittencourt.cursomc.dto;

import br.com.lucas.boni.bittencourt.cursomc.domain.Cliente;
import br.com.lucas.boni.bittencourt.cursomc.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClienteUpdate  
public class ClienteDTO implements Serializable {
    private Integer id;

    @NotEmpty(message = "esse campo é obrigatório")
    @Length(min = 5, max = 120, message = "o tamanho deve ter entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "esse campo é obrigatório")
    @Email(message = "email inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
