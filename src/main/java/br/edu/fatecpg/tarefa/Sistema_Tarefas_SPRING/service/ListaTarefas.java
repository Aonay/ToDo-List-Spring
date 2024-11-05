package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //Deletar no banco
    public void delTarefa(Tarefa tarefa){
        repository.delete(tarefa);
        tarefas.remove(tarefa);
    }

    public Tarefa buscarTarefaID(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    public List<Tarefa> filtrarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Tarefa> filtrarPorPrioridade(int prioridade) {
        return repository.findByPrioridade(prioridade);
    }

    public List<Tarefa> filtrarPorResponsavel(String responsavel) {
        return repository.findByResponsavel(responsavel);
    }

    public int contarPorStatus(String status) {
        return repository.contarPorStatus(status);
    }

    public List<Tarefa> listarOrdenadoPorPrioridade() {
      return repository.encontrarPorPrioridade();
    }
}
