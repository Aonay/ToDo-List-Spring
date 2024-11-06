package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.ListaTarefas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController
public class TarefaController {
    @Autowired
    private final ListaTarefas listaTarefas;
    private static final Scanner scanner = new Scanner(System.in);

    public TarefaController(ListaTarefas listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    @GetMapping("/")
    public List<Tarefa> exibirTodasAsTarefas(){
        return listaTarefas.listarTodas();
    }

    @PostMapping
    public void adicionarTarefa(Tarefa tarefa){
        listaTarefas.addTarefa(tarefa);
    }

}
