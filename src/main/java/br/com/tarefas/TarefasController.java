package br.com.tarefas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private final List<Tarefa> listaDeTarefas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Tarefa>> recuperarTarefa() {
        return ResponseEntity.ok(this.listaDeTarefas);
    }

    @PostMapping
    public ResponseEntity<?> adicionarTarefa(@RequestBody Tarefa tarefa) {
        boolean existe = this.listaDeTarefas.stream().filter(tarefaLista -> tarefaLista.getId() == tarefa.getId()).findFirst().isPresent();
        if (existe){
            return ResponseEntity.badRequest().body(Map.of("messagem", "Tarefa com o ID " + tarefa.getId()+" já existente na lista"));
        }
        this.listaDeTarefas.add(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> recuperarTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefaOptional = this.listaDeTarefas.stream().filter(tarefa -> Objects.equals(tarefa.getId(), id)).findFirst();
        if(tarefaOptional.isPresent()){
            return ResponseEntity.ok(tarefaOptional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
