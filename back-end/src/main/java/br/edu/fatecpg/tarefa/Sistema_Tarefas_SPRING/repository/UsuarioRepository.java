package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}
