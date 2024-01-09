package br.com.empresa.Empresa.domain.tarefa;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "tb_tarefa")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @ManyToMany
    @JoinTable(
            name = "tb_funcionario_tarefa",
            joinColumns = @JoinColumn(name = "id_tarefa"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario"))
    private Set<Funcionario> responsaveis;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private BigDecimal orcamento;

    private String descricao;

    public Tarefa(DadosCadastroTarefa dadosCadastroTarefa, Set<Funcionario> funcionarios) {
        this.nome = dadosCadastroTarefa.nome();
        this.prioridade = dadosCadastroTarefa.prioridade();
        this.responsaveis = funcionarios;
        this.estado = dadosCadastroTarefa.estado();
        this.orcamento = dadosCadastroTarefa.orcamento();
        this.descricao = dadosCadastroTarefa.descricao();
    }
}
