package br.com.capgemini.alunomanagement.adapters.outbound.storage;

import br.com.capgemini.alunomanagement.adapters.outbound.jpa.entity.AlunoEntity;
import br.com.capgemini.alunomanagement.adapters.outbound.jpa.repository.AlunoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class AlunoFotoStorageService {

    // diretório base fixo para manter simples (pode virar property depois)
    private static final String BASE_DIR = "uploads/alunos";

    private final AlunoJpaRepository repository;

    public AlunoFotoStorageService(AlunoJpaRepository repository) {
        this.repository = repository;
    }

    /** Salva a foto como uploads/alunos/{id}.jpg por padrão. */
    public void salvarFoto(Long id, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo de foto vazio.");
        }

        AlunoEntity aluno = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        // garante diretório
        Path dir = Paths.get(BASE_DIR);
        Files.createDirectories(dir);

        // para manter simples: sempre .jpg
        Path destino = dir.resolve(id + ".jpg");

        // grava o arquivo substituindo se já existir
        Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        // persiste o path no aluno
        aluno.setFotoPath(destino.toString());
        repository.save(aluno);
    }

    /** Lê a foto do disco. Se não existir, retorna null. */
    public byte[] carregarFoto(Long id) throws IOException {
        AlunoEntity aluno = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        String path = aluno.getFotoPath();
        if (path == null) return null;

        Path arquivo = Paths.get(path);
        if (!Files.exists(arquivo)) return null;

        return Files.readAllBytes(arquivo);
    }

    public void excluirFotoSeExistir(Long id) {
        repository.findById(id).ifPresent(aluno -> {
            String path = aluno.getFotoPath();
            if (path != null && !path.isBlank()) {
                try {
                    Files.deleteIfExists(Paths.get(path));
                } catch (Exception ignored) {}
            }
        });
    }
}