package br.com.tarefas.repository;

import br.com.tarefas.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

    List<Convidado> findByUsuarioId(Long usuarioId);
}
