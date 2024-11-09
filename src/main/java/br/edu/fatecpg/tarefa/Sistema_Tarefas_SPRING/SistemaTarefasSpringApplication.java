package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.ListaTarefas;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class SistemaTarefasSpringApplication implements CommandLineRunner {

	@Autowired
	private ListaTarefas listaTarefas;
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SistemaTarefasSpringApplication.class, args);
	}

	@Override
	public void run(String... args) {
		while (true) {
			System.out.println("""
                ----------------------------
                GERENCIADOR DE TAREFAS
                1 - Adicionar nova tarefa
                2 - Exibir todas as tarefas
                3 - Exibir tarefa específica
                4 - Atualizar responsável
                5 - Atualizar status
                6 - Filtrar por prioridade
                7 - Filtrar por status
                8 - Excluir tarefa
                0 - Sair
                ----------------------------""");

			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer do scanner

			switch (opcao) {
				case 1 -> adicionarTarefa();
				case 2 -> exibirTodasAsTarefas();
				case 3 -> exibirTarefaEspecifica();
				case 4 -> atualizarResponsavel();
				case 5 -> atualizarStatus();
				case 6 -> filtrarPorPrioridade();
				case 7 -> filtrarPorStatus();
				case 8 -> excluirTarefaPorId();
				case 0 -> {
					System.out.println("Saindo...");
					scanner.close();
					return;
				}
				default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private void adicionarTarefa() {
		System.out.println("Título da tarefa:");
		String titulo = scanner.nextLine().toLowerCase();

		System.out.println("Descrição da tarefa:");
		String descricao = scanner.nextLine().toLowerCase();

		System.out.println("Prioridade da tarefa (1-5):");
		int prioridade = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Responsável pela tarefa:");
		String responsavel = scanner.nextLine().toLowerCase();

		Tarefa tarefa = new Tarefa(titulo, descricao, prioridade, responsavel);
		listaTarefas.salvarTarefa(tarefa);
		System.out.println("Tarefa adicionada com sucesso.");
	}

	private void exibirTodasAsTarefas() {
		listaTarefas.listarTodas().forEach(System.out::println);
	}

	private void exibirTarefaEspecifica() {
		System.out.println("Digite o ID da tarefa:");
		Long id = scanner.nextLong();
		listaTarefas.exibirTarefa(id);
	}

	private void atualizarResponsavel() {
		System.out.println("Digite o ID da tarefa:");
		Long id = scanner.nextLong();
		scanner.nextLine(); // Limpa o buffer do scanner

		System.out.println("Digite o novo responsável:");
		String novoResponsavel = scanner.nextLine();

		listaTarefas.atualizarResponsavelTarefa(id, novoResponsavel);
		System.out.println("Responsável atualizado com sucesso.");
	}

	private void atualizarStatus() {
		String novoStatus;
		System.out.println("Digite o ID da tarefa:");
		Long id = scanner.nextLong();
		scanner.nextLine(); // Limpa o buffer do scanner

		System.out.println("Digite o novo status:");
		System.out.println("""
				1 - Pendente
				2 - Em Andamento
				3 - Concluído
				""");

		int opcao = scanner.nextInt();
		switch(opcao) {
			case 1:
				novoStatus = "pendente";
				listaTarefas.atualizarStatusTarefa(id, novoStatus);
			case 2:
				novoStatus = "em andamento";
				listaTarefas.atualizarStatusTarefa(id, novoStatus);
			case 3:
				novoStatus = "concluído";
				listaTarefas.atualizarStatusTarefa(id, novoStatus);
		}
		System.out.println("Status atualizado com sucesso.");
	}

	private void filtrarPorPrioridade() {
		System.out.println("Digite a prioridade (1-5):");
		int prioridade = scanner.nextInt();
		listaTarefas.filtrarPorPrioridade(prioridade).forEach(System.out::println);
	}

	private void filtrarPorStatus() {
		System.out.println("Digite o status para filtrar:");
		String status = scanner.nextLine();
		listaTarefas.filtrarPorStatus(status).forEach(System.out::println);
	}

	private void excluirTarefaPorId(){
		System.out.println("Digite o ID da tarefa que deseja excluir: ");
		Long id = scanner.nextLong();
		listaTarefas.excluirTarefa(id);
		System.out.println("Tarefa excluada com sucesso.");

	}
}

//		Tarefa tarefa = new Tarefa("Te6Po" ,"Descricao", 2, "Não", "Alguem" );
//		listaTarefas.addTarefa(tarefa);

//		//Query Nativa
//		int contarTarefas = listaTarefas.contarPorStatus("Feito");
//		System.out.println(contarTarefas);
//
//		//JPQL
//		List<Tarefa> tarefasOrdenadas = listaTarefas.listarOrdenadoPorPrioridade();
//		tarefasOrdenadas.forEach(System.out::println);
//
//		DERIVADAS
//		List<Tarefa> responsaveis = listaTarefas.filtrarPorResponsavel("Eu Mesmo");
//		System.out.println(responsaveis);
//
//		List<Tarefa> status = listaTarefas.filtrarPorStatus("Feito");
//		status.forEach(System.out::println);
//
//		List<Tarefa> prioridade = listaTarefas.filtrarPorPrioridade(2);
//		prioridade.forEach(System.out::println);
//
//		listaTarefas.atualizarStatusTarefa(5L, "testandoooooooooo");
//
//		listaTarefas.atualizarResponsavelTarefa(3L, "Alan mesmo");
//
//		listaTarefas.excluirTarefa(5L);
//
//		listaTarefas.exibirTarefa(3L);
