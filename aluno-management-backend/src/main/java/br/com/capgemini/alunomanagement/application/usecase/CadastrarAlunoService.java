package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.model.StatusAluno;
import br.com.capgemini.alunomanagement.domain.ports.in.CadastrarAlunoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarAlunoService implements CadastrarAlunoUseCase {

    //Injeção de dependência
    private final AlunoRepositoryPort repository;

    public CadastrarAlunoService(AlunoRepositoryPort repository) {
        this.repository = repository;
    }

    //Recebe objeto novoAluno contendo seus dados.
    @Override
    public Aluno criarAluno(NovoAlunoCriado novoAluno) {

        //Gera uma nova matrícula.
        String matriculaGerada = gerarMatricula();

        //Cria um objeto de domínio Aluno
        Aluno aluno = new Aluno(
                null,
                novoAluno.nome(),
                matriculaGerada,
                novoAluno.cpf(),
                novoAluno.email(),
                novoAluno.telefone(),
                StatusAluno.ATIVO,
                LocalDateTime.now()
        );
        //salva no repository e retorna aluno criado.
        return repository.salvar(aluno);
    }

    //busca a última matrícula do banco usando o repositório e incrementa+1
    private String gerarMatricula() {
        String ultimaMatricula = repository.buscarUltimaMatricula();
        if (ultimaMatricula == null) {
            return "20260001";
        }
        long proximoNumero = Long.parseLong(ultimaMatricula) + 1;
        return String.valueOf(proximoNumero);
    }
}