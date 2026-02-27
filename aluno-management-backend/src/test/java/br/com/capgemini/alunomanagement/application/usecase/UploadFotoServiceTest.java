// UploadFotoServiceTest
package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.ports.out.AlunoFotoStoragePort;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.mockito.Mockito.*;

class UploadFotoServiceTest {

    @Test
    void deveVerificarExistenciaAntesDeSalvarFoto() throws Exception {
        AlunoRepositoryPort repo = mock(AlunoRepositoryPort.class);
        AlunoFotoStoragePort storage = mock(AlunoFotoStoragePort.class);
        MultipartFile file = mock(MultipartFile.class);
        var service = new UploadFotoService(repo, storage);

        when(repo.porId(1L)).thenReturn(Optional.ofNullable(new br.com.capgemini.alunomanagement.domain.model.Aluno()));
        service.salvarFoto(1L, file);

        verify(repo).porId(1L);
        verify(storage).salvarFoto(1L, file);
    }
}