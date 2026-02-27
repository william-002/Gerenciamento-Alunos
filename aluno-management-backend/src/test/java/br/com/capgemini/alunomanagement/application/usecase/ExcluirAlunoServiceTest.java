package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.ports.out.AlunoFotoStoragePort;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ExcluirAlunoServiceTest {

    @Test
    void deveLancarQuandoIdNaoExiste() {
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        AlunoFotoStoragePort storage = mock(AlunoFotoStoragePort.class);
        var service = new ExcluirAlunoService(repo, storage);

        when(repo.idNaoExiste(10L)).thenReturn(true);

        assertThatThrownBy(() -> service.deletar(10L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Aluno não encontrado");
        verify(storage, never()).excluirFotoSeExistir(anyLong());
        verify(repo, never()).excluir(anyLong());
    }

    @Test
    void deveExcluirFotoEAposExcluirRegistroQuandoIdExiste() {
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        AlunoFotoStoragePort storage = mock(AlunoFotoStoragePort.class);
        var service = new ExcluirAlunoService(repo, storage);

        when(repo.idNaoExiste(10L)).thenReturn(false);

        service.deletar(10L);

        verify(storage).excluirFotoSeExistir(10L);
        verify(repo).excluir(10L);
    }
}