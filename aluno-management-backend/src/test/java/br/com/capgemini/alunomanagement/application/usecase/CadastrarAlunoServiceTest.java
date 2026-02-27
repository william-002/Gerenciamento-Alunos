package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.model.StatusAluno;
import br.com.capgemini.alunomanagement.domain.ports.in.CadastrarAlunoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CadastrarAlunoServiceTest {

    @Test
    void deveCriarAlunoComMatriculaGeradaEStatusAtivo() {
        // Arrange
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        var service = new CadastrarAlunoService(repo);

        // Simula que não existe matrícula anterior
        when(repo.buscarUltimaMatricula()).thenReturn(null);

        // Captura o Aluno que será salvo
        ArgumentCaptor<Aluno> captor = ArgumentCaptor.forClass(Aluno.class);

        // Simula retorno do save (repo devolve o mesmo aluno com id)
        when(repo.salvar(any(Aluno.class))).thenAnswer(inv -> {
            Aluno a = inv.getArgument(0);
            return new Aluno(1L, a.getNome(), a.getMatricula(), a.getCpf(), a.getEmail(),
                    a.getTelefone(), a.getStatus(), a.getDataCadastro());
        });

        var novo = new CadastrarAlunoUseCase.NovoAlunoCriado("Maria", "11122233344", "maria@ex.com", "11999999999");

        // Act
        Aluno criado = service.criarAluno(novo);

        // Assert
        verify(repo).buscarUltimaMatricula();
        verify(repo).salvar(captor.capture());
        Aluno salvo = captor.getValue();

        assertThat(salvo.getStatus()).isEqualTo(StatusAluno.ATIVO);
        assertThat(salvo.getMatricula()).isEqualTo("20260001");
        assertThat(criado.getId()).isEqualTo(1L);
    }
}