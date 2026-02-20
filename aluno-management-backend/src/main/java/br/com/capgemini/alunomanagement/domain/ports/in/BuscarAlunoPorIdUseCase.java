package br.com.capgemini.alunomanagement.domain.ports.in;

import br.com.capgemini.alunomanagement.domain.model.Aluno;

//domínio deve ser capaz de buscar um aluno pelo id.
public interface BuscarAlunoPorIdUseCase {
   Aluno buscarId(Long id);
}
