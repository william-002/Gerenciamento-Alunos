package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.ports.in.BuscarAlunoPorIdUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.stereotype.Service;

//Buscar um aluno específico pelo id.
@Service
public class BuscarAlunoPorIdService implements BuscarAlunoPorIdUseCase {

    //Injeção de dependência
    private final AlunoRepositoryPort repository;

    public BuscarAlunoPorIdService(AlunoRepositoryPort repository) {
        this.repository = repository;
    }

    //Chama repository por id, Se não encontrar lança exceção
    @Override
    public Aluno buscarId(Long id) {
        return repository.porId(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
