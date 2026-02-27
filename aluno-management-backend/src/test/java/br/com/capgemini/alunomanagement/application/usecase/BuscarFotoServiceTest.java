// BuscarFotoServiceTest
package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.ports.out.AlunoFotoStoragePort;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class BuscarFotoServiceTest {

    @Test
    void deveVerificarExistenciaAntesDeCarregarFoto() throws Exception {
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        AlunoFotoStoragePort storage = mock(AlunoFotoStoragePort.class);
        var service = new BuscarFotoService(repo, storage);

        when(repo.porId(1L)).thenReturn(Optional.of(new br.com.capgemini.alunomanagement.domain.model.Aluno()));
        byte[] expected = new byte[]{1,2,3};
        when(storage.carregarFoto(1L)).thenReturn(expected);

        byte[] bytes = service.carregarFoto(1L);

        verify(repo).porId(1L);
        verify(storage).carregarFoto(1L);
    }
}