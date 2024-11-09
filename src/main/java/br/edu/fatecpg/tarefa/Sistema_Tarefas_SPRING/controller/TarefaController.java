package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Tarefa;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.ListaTarefas;
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
    private ListaTarefas listaTarefas;

    @GetMapping
    public ResponseEntity<List<Tarefa>> exibirTarefas(){
       List<Tarefa> tarefas = listaTarefas.listarTodas();
       return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
       Optional<Tarefa> tarefa = listaTarefas.buscarTarefaPorId(id);
       return tarefa.map(ResponseEntity::ok)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    @PostMapping("/")
    public ResponseEntity<Tarefa> criarTarefa(@Validated @RequestBody Tarefa tarefa) {
       Tarefa novaTarefa = listaTarefas.salvarTarefa(tarefa);
       return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Validated @RequestBody Tarefa tarefaAtualizada) {
       return listaTarefas.buscarTarefaPorId(id)
               .map(tarefa -> {
                  tarefa.setTitulo(tarefaAtualizada.getTitulo());
                  tarefa.setDescricao(tarefaAtualizada.getDescricao());
                  tarefa.setStatus(tarefaAtualizada.getStatus());
                  tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
                  tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
                  Tarefa tarefaAtualizadaSalva = listaTarefas.salvarTarefa(tarefa);
                  return ResponseEntity.ok(tarefaAtualizadaSalva);
               })
               .orElseGet(() -> {
                  tarefaAtualizada.setId(id);
                  Tarefa novaTarefa = listaTarefas.salvarTarefa(tarefaAtualizada);
                  return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
               });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
       Optional<Tarefa> tarefa = listaTarefas.buscarTarefaPorId(id);
       if (tarefa.isPresent()) {
          listaTarefas.excluirTarefa(id);
          return ResponseEntity.noContent().build();
       } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
       }
    }
}
