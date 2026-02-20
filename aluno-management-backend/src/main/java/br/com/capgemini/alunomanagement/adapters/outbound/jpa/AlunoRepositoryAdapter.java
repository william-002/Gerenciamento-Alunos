package br.com.capgemini.alunomanagement.adapters.outbound.jpa;

import br.com.capgemini.alunomanagement.adapters.outbound.jpa.entity.AlunoEntity;
import br.com.capgemini.alunomanagement.adapters.outbound.jpa.mapper.AlunoMapper;
import br.com.capgemini.alunomanagement.adapters.outbound.jpa.repository.AlunoJpaRepository;
import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

//Recebe objetos do dominio, faz conversão para entity
@Component
public class AlunoRepositoryAdapter implements AlunoRepositoryPort {

    private final AlunoJpaRepository repository;

    public AlunoRepositoryAdapter(AlunoJpaRepository repository) {
        this.repository = repository;
    }

    //converte dominio para entidade, salva no jpa e converte de volta para dominio
    @Override
    public Aluno salvar(Aluno aluno) {
        AlunoEntity entity = AlunoMapper.toEntity(aluno);
        return AlunoMapper.toDomain(repository.save(entity));
    }

    //Busca maior matricula no banco
    @Override
    public String buscarUltimaMatricula() {
        return repository.buscarUltimaMatricula();
    }

   //Se não tiver nada lista tudo, se busca é feito por numeros busca matricula, senão busca por nome
    @Override
    public Page<Aluno> buscarPaginado(String filtro, Pageable pageable) {
        if (filtro == null || filtro.isBlank()) {
            return repository.findAll(pageable).map(AlunoMapper::toDomain);
        }
        if (filtro.matches("\\d+")) {
            return repository.findByMatriculaContaining(filtro, pageable).map(AlunoMapper::toDomain);
        }
        return repository.findByNomeStartingWith(filtro, pageable).map(AlunoMapper::toDomain);
    }

    //Busca aluno no banco por id.
    @Override
    public Optional<Aluno> porId(Long id) {
        return repository.findById(id).map(AlunoMapper::toDomain);
    }

    //Verficia se id existe no banco.
    @Override
    public boolean idNaoExiste(Long id) {
        return !repository.existsById(id);
    }

    //Exclui aluno por id
    @Override
    public void excluir(Long id) {
      repository.deleteById(id);
    }


}



