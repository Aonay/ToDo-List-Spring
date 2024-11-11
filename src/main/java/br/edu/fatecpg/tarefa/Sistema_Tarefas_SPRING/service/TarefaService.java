package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository.TarefasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefasRepository repository;

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return repository.findById(id);
    }

    public List<Tarefa> buscarTodas() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Tarefa> atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        return buscarTarefaPorId(id).map(tarefaExistente -> {
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setStatus(tarefaAtualizada.getStatus());
            tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
            tarefaExistente.setResponsavel(tarefaAtualizada.getResponsavel());
            return repository.save(tarefaExistente);
        });
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