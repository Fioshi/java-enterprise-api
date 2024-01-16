package br.com.empresa.Empresa.domain.historico;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.tarefa.Estado;
import br.com.empresa.Empresa.domain.tarefa.Prioridade;
import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_historico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String descricao;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_tarefa")
    private Tarefa tarefa;

    public Historico (Tarefa tarefa) {
        this.estado = tarefa.getEstado();
        this.descricao = tarefa.getDescricao();
        this.data = tarefa.getData();
        this.tarefa = tarefa;
    }
}
