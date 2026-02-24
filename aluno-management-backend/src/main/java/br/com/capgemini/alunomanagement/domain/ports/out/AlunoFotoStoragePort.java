package br.com.capgemini.alunomanagement.domain.ports.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlunoFotoStoragePort {

    void salvarFoto(Long id, MultipartFile foto) throws IOException;

    byte[] carregarFoto(Long id) throws IOException;

    void excluirFotoSeExistir(Long id);
}
