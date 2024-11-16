package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
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
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Retorna todas as tarefas cadastradas")
    @GetMapping
    public ResponseEntity<List<Tarefa>> exibirTarefas(){
       List<Tarefa> tarefas = tarefaService.buscarTodas();
       return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> exibirTarefaPorID(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
        return tarefa.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }


    @PostMapping("/")
    public ResponseEntity<Tarefa> criarTarefa(@Validated @RequestBody Tarefa tarefa, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        Tarefa novaTarefa = tarefaService.salvarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Validated @RequestBody Tarefa tarefaAtualizada, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        Optional<Tarefa> tarefaAtulizada = tarefaService.atualizarTarefa(id, tarefaAtualizada);
        return tarefaAtulizada
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
        if (tarefa.isPresent()) {
            tarefaService.excluirTarefa(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
    }

    @GetMapping("/prioridade/{prioridade}")
    public ResponseEntity<List<Tarefa>> exibirPorPrioridade(@PathVariable int prioridade, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        List<Tarefa> tarefaPrioridade = tarefaService.filtrarPorPrioridade(prioridade);
       if(tarefaPrioridade.isEmpty()){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tarefaPrioridade);
       }
       return ResponseEntity.ok(tarefaPrioridade);
    }

   @GetMapping("/listar_prioridade")
   public ResponseEntity<List<Tarefa>> listarOrdenadoPorPrioridade(HttpSession session) {
       if (session.getAttribute("usuario") == null) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                   .body(null);
       }
        List<Tarefa> tarefas = tarefaService.listarOrdenadoPorPrioridade();
      return ResponseEntity.ok(tarefas);
   }

   @GetMapping("/status/{status}")
   public ResponseEntity<List<Tarefa>> exibirPorStatus(@PathVariable String status, HttpSession session) {
       if (session.getAttribute("usuario") == null) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                   .body(null);
       }
        List<Tarefa> tarefaStatus = tarefaService.filtrarPorStatus(status);
      if(tarefaStatus.isEmpty()){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tarefaStatus);
      }
      return ResponseEntity.ok(tarefaStatus);
   }

   @GetMapping("/ordenar")
   public ResponseEntity<List<Tarefa>> ordenarPorTitulo(HttpSession session){
       if (session.getAttribute("usuario") == null) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                   .body(null);
       }
        List<Tarefa> tarefas = tarefaService.ordenarPorTitulo();
        return ResponseEntity.ok(tarefas);
   }

    @PostMapping("/enviarEmail/{$email}")
    public ResponseEntity<String> enviarEmailCadastro(@RequestParam @Email String email, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        try {
            emailService.enviarEmail(email, "Bem-vindo!",
                    "Obrigado por se cadastrar na nossa plataforma.");
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao enviar o e-mail.");
        }
    }
}
