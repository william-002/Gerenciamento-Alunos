package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.model.StatusAluno;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class BuscarAlunoPorIdServiceTest {

    @Test
    void deveLancarExcecaoQuandoNaoEncontrado() {
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        var service = new BuscarAlunoPorIdService(repo);

        when(repo.porId(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Aluno não encontrado");
    }

    @Test
    void deveRetornarAlunoQuandoEncontrado() {
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        var service = new BuscarAlunoPorIdService(repo);

        var aluno = new Aluno(1L, "João", "20260002", "98765432100", "j@ex.com", "11999990000",
                StatusAluno.ATIVO, LocalDateTime.now());
        when(repo.porId(1L)).thenReturn(Optional.of(aluno));

        var result = service.buscarId(1L);
        assert result.getId().equals(1L);
    }
}