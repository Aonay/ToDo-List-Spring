package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

   List<Tarefa> findByUsuario(Usuario usuario);
   List<Tarefa> findByPrioridadeAndUsuario(int prioridade, Usuario usuario);
   List<Tarefa> findByResponsavelAndUsuario(String responsavel, Usuario usuario);
   List<Tarefa> findByStatusAndUsuario(String status, Usuario usuario);
   Optional<Tarefa> findByIdAndUsuario(Long id, Usuario usuario);
   List<Tarefa> findAllByOrderByTituloAsc(); //Arrumar

   //NATIVA
   @Query(value = "SELECT COUNT(*) FROM Tarefas WHERE status = :status", nativeQuery = true)
   int contarPorStatus(@Param("status") String status);

   @Query("SELECT t.status, COUNT(t) FROM Tarefa t GROUP BY t.status")
   Map<String, Long> contarTarefasPorStatus(@Param("usuario") Usuario usuario);

   //JQPL
   @Query("SELECT t FROM Tarefa t ORDER BY t.prioridade ASC")
   List<Tarefa> encontrarPorPrioridade();

   @Modifying
   @Transactional
   @Query("UPDATE Tarefa t SET t.status = :novoStatus WHERE t.id = :id")
   void atualizarStatusPorId(Long id, String novoStatus);

   @Modifying
   @Transactional
   @Query("UPDATE Tarefa t SET t.responsavel = :novoResponsavel WHERE t.id = :id")
   void atualizarResponsavelPorId(Long id, String novoResponsavel);

   @Modifying
   @Transactional
   @Query("DELETE Tarefa t WHERE t.id = :id")
   void excluirPorId(Long id);
}
