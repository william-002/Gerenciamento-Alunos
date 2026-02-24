package br.com.capgemini.alunomanagement.application.usecase;


import br.com.capgemini.alunomanagement.domain.ports.in.ExcluirAlunoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoFotoStoragePort;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.stereotype.Service;

//Remover aluno e foto do sistema.
@Service
public class ExcluirAlunoService implements ExcluirAlunoUseCase {

    //Injeção de dependências
    private final AlunoRepositoryPort repository;

    private final AlunoFotoStoragePort fotoStoragePort;


    public ExcluirAlunoService(AlunoRepositoryPort repository, AlunoFotoStoragePort fotoStoragePort) {
        this.repository = repository;
        this.fotoStoragePort = fotoStoragePort;
    }

    public void deletar(Long id){
        //Checa se id existe
        if(repository.idNaoExiste(id)){
           throw new RuntimeException("Aluno não encontrado");
        }
        // Exclui arquivo de foto se houver e exclui registro do banco
        fotoStoragePort.excluirFotoSeExistir(id);
        repository.excluir(id);
    }
}
