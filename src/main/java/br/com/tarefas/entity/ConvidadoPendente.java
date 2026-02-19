package br.com.tarefas.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity(name = "convidado_pendente")
@Table(name = "convidado_pendente")
@NoArgsConstructor
@RequiredArgsConstructor
public class ConvidadoPendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "tarefa_id", nullable = false)
    private Tarefa tarefa;

    @NonNull
    @Column(name = "convidado_nome", nullable = false)
    private String convidadoNome;

    @NonNull
    @Column(name = "convidado_email", nullable = false)
    private String convidadoEmail;


}
