package br.com.capgemini.alunomanagement.adapters.outbound.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//Entidade mapeada para tabela alunos no banco.
@Entity
@Table(name = "alunos")
public class AlunoEntity {

    //Chave primária gerada automaticamente pelo banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String matricula;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;
    //grava o texto do enum no banco
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAlunoEntity status;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;
    //Guarda o caminho do arquivo da foto no disco, para depois carregar a imagem.
    @Column(name = "foto_path")
    private String fotoPath;


    public AlunoEntity() {
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

    public StatusAlunoEntity getStatus() {
        return status;
    }

    public void setStatus(StatusAlunoEntity status) {
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
