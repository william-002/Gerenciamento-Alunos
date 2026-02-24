package br.com.capgemini.alunomanagement.adapters.inbound.rest.controller;

import br.com.capgemini.alunomanagement.adapters.inbound.rest.dto.AlunoDetalheDTO;
import br.com.capgemini.alunomanagement.adapters.inbound.rest.dto.AlunoRequestDTO;
import br.com.capgemini.alunomanagement.adapters.inbound.rest.dto.AlunoResponseDTO;
import br.com.capgemini.alunomanagement.adapters.inbound.rest.dto.AlunoUpdateDTO;
import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.ports.in.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//Controller recebe requisições do front, valida os dados, chama o service, converte informações entre dto e domain
//envia a resposta no formato esperado pelo front.
@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "http://localhost:4200")
public class AlunoController {

    private final CadastrarAlunoUseCase cadastrarAlunoUseCase;

    private final ListarAlunosUseCase listarAlunosUseCase;

    private final BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;

    private final EditarAlunoUseCase editarAlunoUseCase;

    private final ExcluirAlunoUseCase excluirAlunoUseCase;

    private final UploadFotoUseCase uploadFotoUseCase;

    private final BuscarFotoUseCase buscarFotoUseCase;


    public AlunoController(CadastrarAlunoUseCase cadastrarAlunoUseCase, ListarAlunosUseCase listarAlunosUseCase, BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase, EditarAlunoUseCase editarAlunoUseCase, ExcluirAlunoUseCase excluirAlunoUseCase, UploadFotoUseCase uploadFotoUseCase, BuscarFotoUseCase buscarFotoUseCase) {
        this.cadastrarAlunoUseCase = cadastrarAlunoUseCase;
        this.listarAlunosUseCase = listarAlunosUseCase;
        this.buscarAlunoPorIdUseCase = buscarAlunoPorIdUseCase;
        this.editarAlunoUseCase = editarAlunoUseCase;
        this.excluirAlunoUseCase = excluirAlunoUseCase;
        this.uploadFotoUseCase = uploadFotoUseCase;
        this.buscarFotoUseCase = buscarFotoUseCase;
    }


    //Recebe o formulário de AlunoRequestDTO, valida os dados.
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrar(@RequestBody @Valid AlunoRequestDTO requestDTO) {

        //Monta objeto NovoAlunoCriado
        var dados = new CadastrarAlunoUseCase.NovoAlunoCriado(
                requestDTO.getNome(),
                requestDTO.getCpf(),
                requestDTO.getEmail(),
                requestDTO.getTelefone()
        );

        //Chama a interface de serviço usecase recebendo os dados de novo aluno e salvando eles.
        Aluno aluno = cadastrarAlunoUseCase.criarAluno(dados);

        //Devolve o ResponseDTO com dados básicos
        var response = new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getEmail(),
                aluno.getStatus().name()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

     //Busca alunos com filtro e paginação.
    @GetMapping
    public Page<AlunoResponseDTO> listar(
            @RequestParam(required = false) String filtro, @PageableDefault(size = 50) Pageable pageable){

        //Converte domain para dto e retorna os elementos da lista.
        return listarAlunosUseCase.buscar(filtro, pageable).map(a -> new AlunoResponseDTO(a.getId(), a.getNome(),
                a.getMatricula(), a.getEmail(), a.getStatus().name()
        ));
    }

    //Busca o aluno por id, monta o AlunoDetalheDTO com todos os dados que serão retornados
    //adiciona a fotoUrl no padrão
    @GetMapping("/{id}")
    public AlunoDetalheDTO buscarPorId(@PathVariable Long id){
      var a =  buscarAlunoPorIdUseCase.buscarId(id);
        String fotoUrl = "http://localhost:8080/alunos/" + a.getId() + "/foto";

      return new AlunoDetalheDTO(a.getId(), a.getNome(), a.getMatricula(), a.getCpf(), a.getEmail(),
              a.getTelefone(), a.getStatus().name(), a.getDataCadastro(), fotoUrl );
    }

    //Recebe os dados que podem ser modificados, edita os dados do aluno e depois salva atualização.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Long id, @RequestBody @Valid AlunoUpdateDTO dto){
        var aluno = new EditarAlunoUseCase.EditarAlunoAtual(dto.getEmail(), dto.getTelefone(), dto.getStatus());
        editarAlunoUseCase.atualizar(id, aluno);
    }
     //Chama método do service que busca por id e apaga o registro no banco.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){

        excluirAlunoUseCase.deletar(id);
    }

    // Recebe um MultipartFile.
    @PostMapping("/{id}/foto")
    public ResponseEntity<Void> uploadFoto(@PathVariable Long id,
                                           @RequestParam("foto") MultipartFile foto) throws IOException {
        //Verifica se está vazio
        if (foto == null || foto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        //Chama método e salva a foto.
        uploadFotoUseCase.salvarFoto(id, foto);
        return ResponseEntity.noContent().build();
    }

    //Lê o arquivo da foto
    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> downloadFoto(@PathVariable Long id) throws IOException {
        //Se não existir, retorna 404
        byte[] bytes = buscarFotoUseCase.carregarFoto(id);
        if (bytes == null) return ResponseEntity.notFound().build();
       //Retorna em bytes com "image/jpeg"
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(bytes);
    }

}
