package br.com.capgemini.alunomanagement.adapters.outbound.jpa.mapper;

import br.com.capgemini.alunomanagement.adapters.outbound.jpa.entity.AlunoEntity;
import br.com.capgemini.alunomanagement.adapters.outbound.jpa.entity.StatusAlunoEntity;
import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.model.StatusAluno;

public class AlunoMapper {

    // Domínio converte para Entidade (para salvar no banco)
    public static AlunoEntity toEntity(Aluno aluno){
        AlunoEntity entity = new AlunoEntity();
        entity.setId(aluno.getId());
        entity.setNome(aluno.getNome());
        entity.setMatricula(aluno.getMatricula());
        entity.setCpf(aluno.getCpf());
        entity.setEmail(aluno.getEmail());
        entity.setTelefone(aluno.getTelefone());
        entity.setStatus(mapStatusToEntity(aluno.getStatus()));
        entity.setDataCadastro(aluno.getDataCadastro());
        entity.setFotoPath(aluno.getFotoPath());
        return entity;

    }
    // Entidade converte para Domínio (para usar na lógica da aplicação)
    public static Aluno toDomain(AlunoEntity entity){
        Aluno aluno = new Aluno(
                entity.getId(),
                entity.getNome(),
                entity.getMatricula(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getTelefone(),
                mapStatusToDomain(entity.getStatus()),
                entity.getDataCadastro()

        );

        aluno.setFotoPath(entity.getFotoPath());

        return aluno;

    }
  //Converte o enum de domínio (StatusAluno) para o enum da entidade (StatusAlunoEntity).
    private static StatusAlunoEntity mapStatusToEntity(StatusAluno status){
        return StatusAlunoEntity.valueOf(status.name());
    }
    //Converte o enum de entidade (StatusAlunoEntity)para o enum da domínio (StatusAluno).
    private static StatusAluno mapStatusToDomain(StatusAlunoEntity status){
        return StatusAluno.valueOf(status.name());
    }
}
