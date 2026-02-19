package br.com.tarefas.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity(name = "convidados")
@Table(name = "convidados")
@RequiredArgsConstructor
@NoArgsConstructor
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn
    private Tarefa tarefa;

    @NonNull
    @ManyToOne
    @JoinColumn
    private Usuario usuario;

}
