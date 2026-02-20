package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.ports.in.ListarAlunosUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//Listar alunos usando filtro e paginação
@Service
public class ListarAlunosService implements ListarAlunosUseCase {

    //Injeção de dependências
    private final AlunoRepositoryPort repository;

    public ListarAlunosService(AlunoRepositoryPort repository) {
        this.repository = repository;
    }

    //Recebe um filtro que pode ser nome ou matricula.
    @Override
    public Page<Aluno> buscar(String filtro, Pageable pageable) {
        //chama repository que decide se busca por nome ou matricula
        return repository.buscarPaginado(filtro, pageable);
    }
}
