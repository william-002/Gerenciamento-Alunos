package br.com.capgemini.alunomanagement.domain.model;

import java.time.LocalDateTime;

//Modelo principal do sistema, que representa o aluno no mundo da regra de negócio.
public class Aluno {

    private Long id;
    private String nome;
    private String matricula;
    private String cpf;
    private String email;
    private String telefone;
    private StatusAluno status;
    private LocalDateTime dataCadastro;
    private String fotoPath;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String matricula, String cpf, String email,
                 String telefone, StatusAluno status, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public StatusAluno getStatus() {
        return status;
    }

    public void setStatus(StatusAluno status) {
        this.status = status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }
}
