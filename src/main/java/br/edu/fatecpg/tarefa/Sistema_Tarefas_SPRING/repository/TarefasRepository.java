package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository;

import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

   List<Tarefa> findByPrioridade(int prioridade);
   List<Tarefa> findByResponsavel(String responsavel);
   List<Tarefa> findByStatus(String status);

   @Query(value = "SELECT COUNT(*) FROM Tarefas WHERE status = :status", nativeQuery = true)
   int contarPorStatus(@Param("status") String status);

   @Query("SELECT t FROM Tarefa t ORDER BY t.prioridade ASC")
   List<Tarefa> encontrarPorPrioridade();
}
