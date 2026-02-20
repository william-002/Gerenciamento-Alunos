package br.com.capgemini.alunomanagement.domain.ports.in;


//domínio deve conseguir excluir um aluno pelo id.
public interface ExcluirAlunoUseCase {

public void deletar(Long id);

}
