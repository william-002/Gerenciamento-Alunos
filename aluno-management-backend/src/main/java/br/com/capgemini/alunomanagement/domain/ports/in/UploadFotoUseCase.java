package br.com.capgemini.alunomanagement.domain.ports.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFotoUseCase {

    void salvarFoto(Long id, MultipartFile file) throws IOException;
}
