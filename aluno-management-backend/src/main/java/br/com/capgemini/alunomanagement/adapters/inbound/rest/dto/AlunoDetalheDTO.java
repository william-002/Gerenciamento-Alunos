package br.com.capgemini.alunomanagement.adapters.inbound.rest.dto;

import java.time.LocalDateTime;

//Classe para mostrar todos os detalhes do aluno, diferente do ResponseDTO
public class AlunoDetalheDTO {

    private Long id;
    private String nome;
    private String matricula;
    private String cpf;
    private String email;
    private String telefone;
    private String status;
    private LocalDateTime dataCadastro;
    private String fotoUrl;

    public AlunoDetalheDTO(Long id, String nome, String matricula, String cpf, String email, String telefone, String status, LocalDateTime dataCadastro, String fotoUrl) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
        this.dataCadastro = dataCadastro;
        this.fotoUrl = fotoUrl;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }
}
