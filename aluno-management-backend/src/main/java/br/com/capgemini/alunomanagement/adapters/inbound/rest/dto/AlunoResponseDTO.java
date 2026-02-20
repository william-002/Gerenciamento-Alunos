package br.com.capgemini.alunomanagement.adapters.inbound.rest.dto;

//Representando o que o backend devolve ao frontend.
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String status;

    public AlunoResponseDTO(Long id, String nome, String matricula, String email, String status) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}
