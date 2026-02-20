package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.StatusAluno;
import br.com.capgemini.alunomanagement.domain.ports.in.EditarAlunoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.stereotype.Service;

//Editar apenas os dados necessários
@Service
public class EditarAlunoService implements EditarAlunoUseCase {

    //Injeção de dependência
    private final AlunoRepositoryPort repository;

    public EditarAlunoService(AlunoRepositoryPort repository) {
        this.repository = repository;
    }

    //Busca aluno por id e altera os dados específicos
    @Override
    public void atualizar(Long id, EditarAlunoAtual atualizar) {
       var aluno = repository.porId(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

       aluno.setEmail(atualizar.email());
       aluno.setTelefone(atualizar.telefone());
       //converte de String para enum
       aluno.setStatus(Enum.valueOf(StatusAluno.class, atualizar.status()));
        //Salva os novos dados no repository
       repository.salvar(aluno);
    }
}
