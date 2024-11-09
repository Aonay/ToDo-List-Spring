package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ListaTarefas {

    @Autowired
    private TarefasRepository repository;

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return repository.findById(id);
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

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

    public List<Tarefa> filtrarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Tarefa> filtrarPorPrioridade(int prioridade) {
        return repository.findByPrioridade(prioridade);
    }

    public List<Tarefa> filtrarPorResponsavel(String responsavel) {
        return repository.findByResponsavel(responsavel);
    }

    // METODO NATIVO
    public int contarPorStatus(String status) {
        return repository.contarPorStatus(status);
    }

    // METODOS JPQL
    public void excluirTarefa(Long id) {
        repository.excluirPorId(id);
    }
    public List<Tarefa> listarOrdenadoPorPrioridade() {
        return repository.encontrarPorPrioridade();
    }
    public void atualizarStatusTarefa(Long id, String novoStatus) {
        repository.atualizarStatusPorId(id, novoStatus);
    }
    public void atualizarResponsavelTarefa(Long id, String novoResponsavel) {
        repository.atualizarResponsavelPorId(id, novoResponsavel);
    }
}