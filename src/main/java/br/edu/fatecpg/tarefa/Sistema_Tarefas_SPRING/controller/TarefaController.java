package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.EmailService;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
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
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private EmailService emailService;

    @Operation(summary = "Retorna todas as tarefas cadastradas")
    @GetMapping
    public ResponseEntity<List<Tarefa>> exibirTarefas(){
       List<Tarefa> tarefas = tarefaService.buscarTodas();
       return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> exibirTarefaPorID(@PathVariable Long id) {
       Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
       return tarefa.map(ResponseEntity::ok)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    @GetMapping("/prioridade/{prioridade}")
    public ResponseEntity<List<Tarefa>> exibirPorPrioridade(@PathVariable int prioridade) {
       List<Tarefa> tarefaPrioridade = tarefaService.filtrarPorPrioridade(prioridade);
       if(tarefaPrioridade.isEmpty()){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tarefaPrioridade);
       }
       return ResponseEntity.ok(tarefaPrioridade);
    }

   @GetMapping("/listar_prioridade")
   public ResponseEntity<List<Tarefa>> listarOrdenadoPorPrioridade() {
      List<Tarefa> tarefas = tarefaService.listarOrdenadoPorPrioridade();
      return ResponseEntity.ok(tarefas);
   }

   @GetMapping("/status/{status}")
   public ResponseEntity<List<Tarefa>> exibirPorStatus(@PathVariable String status) {
      List<Tarefa> tarefaStatus = tarefaService.filtrarPorStatus(status);
      if(tarefaStatus.isEmpty()){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tarefaStatus);
      }
      return ResponseEntity.ok(tarefaStatus);
   }

    @PostMapping("/")
    public ResponseEntity<Tarefa> criarTarefa(@Validated @RequestBody Tarefa tarefa) {
       Tarefa novaTarefa = tarefaService.salvarTarefa(tarefa);
       return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Validated @RequestBody Tarefa tarefaAtualizada) {
       Optional<Tarefa> tarefaAtulizada = tarefaService.atualizarTarefa(id, tarefaAtualizada);
       return tarefaAtulizada
               .map(ResponseEntity::ok)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
       Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
       if (tarefa.isPresent()) {
          tarefaService.excluirTarefa(id);
          return ResponseEntity.noContent().build();
       } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
       }
    }

    @PostMapping("/enviarEmail/{$email}")
    public ResponseEntity<String> enviarEmailCadastro(@RequestParam @Email String email) {
        try {
            emailService.enviarEmail(email, "Bem-vindo!",
                    "Obrigado por se cadastrar na nossa plataforma.");
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao enviar o e-mail.");
        }
    }
}
