package br.com.capgemini.alunomanagement.adapters.inbound.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

//Representando o que o cliente no front-end pode enviar para o back-end com validações.
public class AlunoRequestDTO {

    @NotBlank
    private String nome;

   // @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^\\d{10,11}$",
            message = "Insira um número válido"
    )
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
