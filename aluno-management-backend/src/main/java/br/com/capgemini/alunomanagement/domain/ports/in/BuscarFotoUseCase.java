package br.com.capgemini.alunomanagement.domain.ports.in;

import java.io.IOException;

public interface BuscarFotoUseCase {

    byte[] carregarFoto(Long id) throws IOException;
}
