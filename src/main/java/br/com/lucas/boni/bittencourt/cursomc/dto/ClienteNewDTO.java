package br.com.lucas.boni.bittencourt.cursomc.dto;

import br.com.lucas.boni.bittencourt.cursomc.services.validation.ClienteInsert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {

    //TELEFONE
    @NotEmpty(message = "esse campo é obrigatório")
    String telefone1;
    String telefone2;
    String telefone3;
    
    //CLIENTE
    @NotEmpty(message = "esse campo é obrigatório")
    @Length(min = 5, max = 120, message = "o tamanho deve ter entre 5 e 120 caracteres")
    private String nome;
    
    @NotEmpty(message = "esse campo é obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotEmpty(message = "esse campo é obrigatório")
    private String cpfOuCnpj;

    private Integer tipoCliente;
    
    //ENDERECO
    @NotEmpty(message = "esse campo é obrigatório")
    private String logradouro;
    @NotEmpty(message = "esse campo é obrigatório")
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty(message = "esse campo é obrigatório")
    private String cep;

    @NotEmpty(message = "esse campo é obrigatório")
    private String senha;


    //CIDADE
    private Integer cidadeId;

    public ClienteNewDTO() {
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
