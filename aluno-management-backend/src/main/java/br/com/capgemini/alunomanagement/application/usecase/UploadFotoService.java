package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.ports.in.UploadFotoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoFotoStoragePort;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadFotoService implements UploadFotoUseCase {

    private final AlunoRepositoryPort repositoryPort;

    private final AlunoFotoStoragePort fotoStoragePort;

    public UploadFotoService(AlunoRepositoryPort repositoryPort, AlunoFotoStoragePort fotoStoragePort) {
        this.repositoryPort = repositoryPort;
        this.fotoStoragePort = fotoStoragePort;
    }

    @Override
    public void salvarFoto(Long id, MultipartFile file) throws IOException {

        repositoryPort.porId(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        fotoStoragePort.salvarFoto(id, file);
    }
}
