package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios/")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario user) {
        Usuario usuario = usuarioService.registrar(user);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario login, HttpSession session) {
        Usuario usuarioLogado = usuarioService.login(login);
        session.setAttribute("usuario", usuarioLogado);
        return ResponseEntity.ok(usuarioLogado);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> deslogarUsuario(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout realizado com sucesso!");
    }
}
