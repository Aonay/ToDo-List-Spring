package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepository;

    public Usuario registrar(Usuario usuario){
//        if(userRepository.findByEmail(usuario.getEmail()).isPresent()){
//            throw new RuntimeException("Email já está em uso");
//        }
        return userRepository.save(usuario);
    }

    public Usuario login(Usuario login) {
        return userRepository.findByEmail(login.getEmail())
                .filter(u -> u.getSenha().equals(login.getSenha()))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}
