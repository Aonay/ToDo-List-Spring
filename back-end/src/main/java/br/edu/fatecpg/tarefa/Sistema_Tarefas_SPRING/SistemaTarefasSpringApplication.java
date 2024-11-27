package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.TarefaService;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class SistemaTarefasSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaTarefasSpringApplication.class, args);
	}
}