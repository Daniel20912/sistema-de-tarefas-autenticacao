package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TarefaDTO recuperarTarefa(Long id) {
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        return modelMapper.map(tarefaOp.orElseThrow(() -> new EntityNotFoundException("Tarefa com o ID " + id + " não encontrado")), TarefaDTO.class);
    }

    public TarefaDTO adicionarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefaEntity = modelMapper.map(tarefaDTO, Tarefa.class);
        return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
    }

    public List<TarefaDTO> recuperaTarefas() {
        return modelMapper.map(tarefaRepository.findAll(), new TypeToken<List<TarefaDTO>>() {}.getType());
    }

    public TarefaDTO atualizaTarefa(Long id, TarefaDTO tarefaDTO) {
        Tarefa tarefaEntity = modelMapper.map(tarefaDTO, Tarefa.class);
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        if (tarefaOp.isPresent()) {
            tarefaEntity.setId(id);
            return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
        }
        throw new EntityNotFoundException("Tarefa com o ID " + id + " não encontrado");
    }

    public void deletarTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa com o ID " + id + " não encontrado");
        }
        tarefaRepository.deleteById(id);
    }
}
