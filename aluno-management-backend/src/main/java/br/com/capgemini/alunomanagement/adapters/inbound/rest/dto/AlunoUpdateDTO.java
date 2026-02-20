package br.com.capgemini.alunomanagement.adapters.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;

//Formulário com os dados que poderão ser editados.
public class AlunoUpdateDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
