package br.com.empresa.Empresa.domain.tarefa;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.historico.Historico;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "tarefa")
    private List<Historico> historico;

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

    private LocalDate data;

    public Tarefa(DadosCadastroTarefa dadosCadastroTarefa, Set<Funcionario> funcionarios ) {
        this.historico = new LinkedList<>();
        this.nome = dadosCadastroTarefa.nome();
        this.prioridade = dadosCadastroTarefa.prioridade();
        this.responsaveis = funcionarios;
        this.estado = dadosCadastroTarefa.estado();
        this.orcamento = dadosCadastroTarefa.orcamento();
        this.descricao = dadosCadastroTarefa.descricao();
        this.data = dadosCadastroTarefa.date();
    }

    @PrePersist
    @PreUpdate
    public void adicionaHistorico(){



    }

    public void atualizar(DadosAtualizacaoTarefa dto) {
        if (dto.descricao() != null)
            this.descricao = dto.descricao();
        if (dto.estado() != null)
            this.estado = dto.estado();
    }
}
