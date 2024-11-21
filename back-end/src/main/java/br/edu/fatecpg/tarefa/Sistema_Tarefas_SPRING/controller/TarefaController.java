package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.EmailService;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.TarefaService;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
@Validated
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private EmailService emailService;

    @Operation(summary = "Retorna todas as tarefas cadastradas")
    @GetMapping
    public ResponseEntity<List<Tarefa>> exibirTarefas(HttpSession session){
       Usuario usuario = (Usuario) session.getAttribute("usuario");
       if (usuario == null) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
       }
       List<Tarefa> tarefas = tarefaService.buscarTodas(usuario);
       return ResponseEntity.ok(tarefas);
    }

    @PostMapping("/")
    public ResponseEntity<Tarefa> criarTarefa(@Validated @RequestBody Tarefa tarefa, HttpSession session) {
       Usuario usuario = (Usuario) session.getAttribute("usuario");
       if (usuario == null) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
       }
       tarefa.setUsuario(usuario);
       Tarefa novaTarefa = tarefaService.salvarTarefa(tarefa);
       return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

   @PutMapping("/{id}")
   public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Validated @RequestBody Tarefa tarefaAtualizada, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      Optional<Tarefa> tarefaExistente = tarefaService.buscarTarefaPorIdEUsuario(id, usuario);
      if (tarefaExistente.isPresent()) {
         tarefaAtualizada.setUsuario(usuario);
         Tarefa tarefaAtualizadaFinal = tarefaService.atualizarTarefa(id, tarefaAtualizada, usuario).orElseThrow();
         return ResponseEntity.ok(tarefaAtualizadaFinal);
      } else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada ou não pertence a você.");
      }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteTarefa(@PathVariable Long id, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorIdEUsuario(id, usuario);
      if (tarefa.isPresent()) {
         tarefaService.excluirTarefa(id);
         return ResponseEntity.noContent().build();
      } else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada ou não pertence a você.");
      }
   }

   @GetMapping("/prioridade/{prioridade}")
   public ResponseEntity<List<Tarefa>> exibirPorPrioridade(@PathVariable int prioridade, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      List<Tarefa> tarefaPrioridade = tarefaService.filtrarPorPrioridade(prioridade, usuario);
      if (tarefaPrioridade.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tarefaPrioridade);
      }

      return ResponseEntity.ok(tarefaPrioridade);
   }

   @GetMapping("/status/{status}")
   public ResponseEntity<List<Tarefa>> exibirPorStatus(@PathVariable String status, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      List<Tarefa> tarefaStatus = tarefaService.filtrarPorStatus(status, usuario);
      if (tarefaStatus.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tarefaStatus);
      }
      return ResponseEntity.ok(tarefaStatus);
   }

//   @GetMapping("/status/count")
//   public ResponseEntity<Map<String, Long>> contarTarefasPorStatus(HttpSession session) {
//      if (session.getAttribute("usuario") == null) {
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//      }
//      Usuario usuario = (Usuario) session.getAttribute("usuario");
//      Map<String, Long> tarefas = tarefaService.contarTarefasPorStatus(usuario);
//      return ResponseEntity.ok(tarefas);
//   }

   @GetMapping("/ordenar")
   public ResponseEntity<List<Tarefa>> ordenarPorTitulo(HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      List<Tarefa> tarefas = tarefaService.ordenarPorTitulo();
      return ResponseEntity.ok(tarefas);
   }

   @PostMapping("/enviarEmail/{email}")
   public ResponseEntity<String> enviarEmailCadastro(@PathVariable @Email String email, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
      try {emailService.enviarEmail(email,
                 "Bem-vindo!",
                 "Obrigado por se cadastrar na nossa plataforma."
         );
         return ResponseEntity.ok("E-mail enviado com sucesso!");
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao enviar o e-mail.");
      }
   }
}
