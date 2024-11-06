package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListaTarefas {
    @Autowired
    private TarefasRepository repository;
    private List<Tarefa> tarefas = new ArrayList<>();

    public ListaTarefas() {
    }

    public void addTarefa(Tarefa tarefa){
        repository.save(tarefa);
        tarefas.add(tarefa);
    }

    public Tarefa buscarTarefaID(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }


    //DERIVADAS

    public List<Tarefa> filtrarPorStatus(String status) { return repository.findByStatus(status); }

    public List<Tarefa> filtrarPorPrioridade(int prioridade) {
        return repository.findByPrioridade(prioridade);
    }

    public List<Tarefa> filtrarPorResponsavel(String responsavel) {
        return repository.findByResponsavel(responsavel);
    }

    //NATIVA

    public int contarPorStatus(String status) {
        return repository.contarPorStatus(status);
    }

    //JPQL

    public List<Tarefa> listarOrdenadoPorPrioridade() {
        return repository.encontrarPorPrioridade();
    }

    public void atualizarStatusTarefa(Long id, String novoStatus) { repository.atualizarStatusPorId(id, novoStatus); }

    public void atualizarResponsavelTarefa(Long id, String novoResponsavel) { repository.atualizarResponsavelPorId(id, novoResponsavel); }

    public void exibirTarefa(Long id) {

        Optional<Tarefa> tarefaOpt = repository.findById(id);

        if (tarefaOpt.isPresent()) {
            Tarefa tarefa = tarefaOpt.get();
            System.out.println("ID: " + tarefa.getId());
            System.out.println("Título: " + tarefa.getTitulo());
            System.out.println("Descrição: " + tarefa.getDescricao());
            System.out.println("Prioridade: " + tarefa.getPrioridade());
            System.out.println("Status: " + tarefa.getStatus());
            System.out.println("Responsável: " + tarefa.getResponsavel());
        } else {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
        }
    }

    public void excluirTarefa(Long id) { repository.excluirPorId(id); }





}
