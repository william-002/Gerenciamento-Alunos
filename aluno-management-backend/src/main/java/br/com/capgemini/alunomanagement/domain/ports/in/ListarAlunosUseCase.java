package br.com.capgemini.alunomanagement.domain.ports.in;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//Permite listar alunos paginados com filtro.
public interface ListarAlunosUseCase {
 Page<Aluno> buscar(String filtro, Pageable pageable);


}
