package br.com.capgemini.alunomanagement.domain.ports.in;


public interface EditarAlunoUseCase {

        //Define o que significa editar um aluno no domínio.
        void atualizar(Long id, EditarAlunoAtual atualizar);
         // Cria o objeto com atributos final, constructors, métodos get,toString, hashCode e equals.
        record EditarAlunoAtual(String email, String telefone, String status){

        }


}
