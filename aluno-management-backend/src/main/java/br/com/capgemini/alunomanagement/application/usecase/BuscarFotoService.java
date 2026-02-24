package br.com.capgemini.alunomanagement.application.usecase;

import br.com.capgemini.alunomanagement.domain.ports.in.BuscarFotoUseCase;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoFotoStoragePort;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BuscarFotoService implements BuscarFotoUseCase {


    private final AlunoRepositoryPort repositoryPort;

    private final AlunoFotoStoragePort fotoStoragePort;

    public BuscarFotoService(AlunoRepositoryPort repositoryPort, AlunoFotoStoragePort fotoStoragePort) {
        this.repositoryPort = repositoryPort;
        this.fotoStoragePort = fotoStoragePort;
    }

    @Override
    public byte[] carregarFoto(Long id) throws IOException {
        repositoryPort.porId(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));


        return fotoStoragePort.carregarFoto(id);
    }


}
