
package br.com.capgemini.alunomanagement.domain.ports.out;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

//Interface que define tudo o que o domínio precisa do repositório(implementada no adapter).
public interface AlunoRepositoryPort {
    Aluno salvar(Aluno aluno);
    String buscarUltimaMatricula();
    Page<Aluno> buscarPaginado(String filtro, Pageable pageable);
    Optional<Aluno> porId(Long id);
    boolean idNaoExiste(Long id);
    void excluir(Long id);
}
