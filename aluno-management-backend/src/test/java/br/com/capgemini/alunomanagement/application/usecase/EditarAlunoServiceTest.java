package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.model.StatusAluno;
import br.com.capgemini.alunomanagement.domain.ports.in.EditarAlunoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

class EditarAlunoServiceTest {

    @Test
    void deveAtualizarEmailTelefoneEStatus() {
        // Arrange
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        var service = new EditarAlunoService(repo);

        var existente = new Aluno(1L, "Ana", "20260001", "12345678900", "email@gmail.com",
                "11988887777", StatusAluno.ATIVO, LocalDateTime.now());
        when(repo.porId(1L)).thenReturn(Optional.of(existente));

        var dto = new EditarAlunoUseCase.EditarAlunoAtual("novo@gmail.com", "11911112222", "INATIVO");

        // Act
        service.atualizar(1L, dto);

        // Assert
        verify(repo).porId(1L);
        verify(repo).salvar(existente);

        // Valores alterados em memória pelo service
        assert existente.getEmail().equals("novo@gmail.com");
        assert existente.getTelefone().equals("11911112222");
        assert existente.getStatus() == StatusAluno.INATIVO; // conversão String->Enum
    }
}