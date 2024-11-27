package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository.TarefasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefasRepository repository;

    public List<Tarefa> filtrarUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Optional<Tarefa> buscarTarefaPorIdEUsuario(Long id, Usuario usuario) {
        return repository.findByIdAndUsuario(id, usuario);
    }

    public List<Tarefa> buscarTodas(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public List<Tarefa> buscarPorStatusEUsuario(String status, Usuario usuario) {
        return repository.findByStatusAndUsuario(status, usuario);
    }


    @Transactional
    public Optional<Tarefa> atualizarTarefa(Long id, Tarefa tarefaAtualizada, Usuario usuario) {
        return buscarTarefaPorIdEUsuario(id, usuario).map(tarefaExistente -> {
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setStatus(tarefaAtualizada.getStatus());
            tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
            tarefaExistente.setResponsavel(tarefaAtualizada.getResponsavel());
            return repository.save(tarefaExistente);
        });
    }

    public List<Tarefa> filtrarPorStatus(String status, Usuario usuario) {
        return repository.findByStatusAndUsuario(status, usuario);
    }

    public List<Tarefa> filtrarPorPrioridade(int prioridade, Usuario usuario) {
        return repository.findByPrioridadeAndUsuario(prioridade, usuario);
    }

    public List<Tarefa> filtrarPorResponsavel(String responsavel, Usuario usuario) {
        return repository.findByResponsavelAndUsuario(responsavel, usuario);
    }

    public List<Tarefa> ordenarPorTitulo(){
        return repository.findAllByOrderByTituloAsc();
    }

    // METODO NATIVO
    public int contarPorStatus(String status) {
        return repository.contarPorStatus(status);
    }

    public Map<String, Long> contarTarefasPorStatus(Usuario usuario) {
        return repository.contarTarefasPorStatus(usuario);
    }


    // METODOS JPQL
    public void excluirTarefa(Long id) {
        repository.excluirPorId(id);
    }

    public void atualizarStatusTarefa(Long id, String novoStatus) {
        repository.atualizarStatusPorId(id, novoStatus);
    }
    public void atualizarResponsavelTarefa(Long id, String novoResponsavel) {
        repository.atualizarResponsavelPorId(id, novoResponsavel);
    }
}