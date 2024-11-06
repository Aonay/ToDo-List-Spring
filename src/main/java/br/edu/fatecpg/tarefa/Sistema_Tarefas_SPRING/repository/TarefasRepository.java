package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository;

import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

   List<Tarefa> findByPrioridade(int prioridade);
   List<Tarefa> findByResponsavel(String responsavel);
   List<Tarefa> findByStatus(String status);
   Optional<Tarefa> findById(Long id);

   //NATIVA
   @Query(value = "SELECT COUNT(*) FROM Tarefas WHERE status = :status", nativeQuery = true)
   int contarPorStatus(@Param("status") String status);

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
