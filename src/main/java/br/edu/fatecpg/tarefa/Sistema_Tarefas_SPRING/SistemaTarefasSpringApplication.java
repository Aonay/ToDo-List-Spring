package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING;

import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.ListaTarefas;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SistemaTarefasSpringApplication implements CommandLineRunner {
	@Autowired
	private ListaTarefas listaTarefas;
	public static void main(String[] args) {
		SpringApplication.run(SistemaTarefasSpringApplication.class, args);
	}

	@Override
	public void run(String... args){
//		Tarefa tarefa = new Tarefa("Te6Po" ,"Descricao", 2, "Não", "Alguem" );
//		listaTarefas.addTarefa(tarefa);

//		//Query Nativa
//		int contarTarefas = listaTarefas.contarPorStatus("Feito");
//		System.out.println(contarTarefas);
//
//		//JPQL
//		List<Tarefa> tarefasOrdenadas = listaTarefas.listarOrdenadoPorPrioridade();
//		tarefasOrdenadas.forEach(System.out::println);

		//DERIVADAS
		List<Tarefa> responsaveis = listaTarefas.filtrarPorResponsavel("Eu Mesmo");
		System.out.println(responsaveis);

		List<Tarefa> status = listaTarefas.filtrarPorStatus("Feito");
		status.forEach(System.out::println);

		List<Tarefa> prioridade = listaTarefas.filtrarPorPrioridade(2);
		prioridade.forEach(System.out::println);

	}
}
