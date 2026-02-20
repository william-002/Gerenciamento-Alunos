package br.com.capgemini.alunomanagement.domain.ports.in;

import br.com.capgemini.alunomanagement.domain.model.Aluno;

public interface CadastrarAlunoUseCase {

    //como um aluno deve ser criado, independente de como vai ser implementado.
    Aluno criarAluno(NovoAlunoCriado novoAluno);
     // Cria o objeto com atributos final, constructors, métodos get,toString, hashCode e equals.
    record NovoAlunoCriado( String nome,
                     String cpf,
                     String email,
                     String telefone){}


}
