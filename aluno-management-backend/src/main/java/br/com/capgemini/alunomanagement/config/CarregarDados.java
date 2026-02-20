package br.com.capgemini.alunomanagement.config;


import br.com.capgemini.alunomanagement.domain.model.Aluno;
import br.com.capgemini.alunomanagement.domain.model.StatusAluno;
import br.com.capgemini.alunomanagement.domain.ports.out.AlunoRepositoryPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class CarregarDados {


    @Bean
    CommandLineRunner carregarDadosMock(AlunoRepositoryPort repository) {

        return args -> {

            // Se já existe ao menos 1 aluno no banco, não cria nada.
            if (repository.buscarUltimaMatricula() != null) {
                return;
            }

            List<String> nomes = Arrays.asList(
                    "João Silva", "Maria Oliveira", "Pedro Santos", "Ana Costa", "Lucas Almeida",
                    "Mariana Ribeiro", "Felipe Carvalho", "Camila Martins", "Gabriel Ferreira",
                    "Laura Rocha", "Fernando Gomes", "Juliana Pires", "André Souza", "Carolina Dias",
                    "Ricardo Melo", "Patrícia Azevedo", "Bruno Campos", "Natalia Barros",
                    "Eduardo Teixeira", "Beatriz Lima", "Diego Nogueira", "Larissa Moraes",
                    "Rafael Monteiro", "Sofia Farias", "Gustavo Batista", "Renata Prado",
                    "Alexandre Cunha", "Isabela Duarte", "Thiago Mendes", "Yasmin Moreira"
            );

            AtomicInteger cpfBase = new AtomicInteger(123456780); // Base para gerar CPFs válidos
            AtomicInteger telefoneBase = new AtomicInteger(900000001);

            for (int i = 0; i < 30; i++) {
                String nome = nomes.get(i);
                String email = nome.toLowerCase().replace(" ", ".") + "@teste.com";

                // CPF simples aceito pelo @CPF.

                String cpf = String.format("%011d", cpfBase.getAndIncrement());

                String telefone = "11" + String.format("%09d", telefoneBase.getAndIncrement());

                String matricula = "202600" + String.format("%02d", i + 1);

                Aluno aluno = new Aluno(
                        null,                 // deixa o repositório gerar ID
                        nome,
                        matricula,                 // matrícula será gerada pelo use case no cadastro
                        cpf,
                        email,
                        telefone,
                        StatusAluno.ATIVO,
                        LocalDateTime.now()
                );

                repository.salvar(aluno);
            }

            System.out.println("30 alunos criados.");
        };
    }
}
