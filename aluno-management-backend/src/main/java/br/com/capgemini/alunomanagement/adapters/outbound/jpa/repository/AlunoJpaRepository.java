package br.com.capgemini.alunomanagement.adapters.outbound.jpa.repository;

import br.com.capgemini.alunomanagement.adapters.outbound.jpa.entity.AlunoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

  //estende jpaRepository para realizar operações de crud
public interface AlunoJpaRepository extends JpaRepository<AlunoEntity, Long> {

    //Query customizada pega a maior matrícula existente (usado para gerar a próxima).
    @Query("select max(a.matricula) from AlunoEntity a")
    String buscarUltimaMatricula();

    //busca por nome começando com a letra digitada
    Page<AlunoEntity> findByNomeStartingWith(String nome, Pageable pageable);
    // busca matrícula contendo o trecho digitado.
    Page<AlunoEntity> findByMatriculaContaining(String matricula, Pageable pageable);
}
