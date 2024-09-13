package br.com.empresa.Empresa.domain.tarefa;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.historico.Historico;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

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
    @Column(name = "id_tarefa")
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

    private LocalDate dataAtt;

    private Double risco;

    public Tarefa(DadosCadastroTarefa dadosCadastroTarefa, Set<Funcionario> funcionarios ) {
        this.historico = new LinkedList<>();
        this.nome = dadosCadastroTarefa.nome();
        this.prioridade = dadosCadastroTarefa.prioridade();
        this.responsaveis = funcionarios;
        this.orcamento = dadosCadastroTarefa.orcamento();
        this.descricao = dadosCadastroTarefa.descricao();
        this.data = dadosCadastroTarefa.data();
        this.dataAtt = LocalDate.now();
        this.estado = Estado.PENDENTE;
    }

    public void atualizar(DadosAtualizacaoTarefa dto) {
        if (dto.descricao() != null)
            this.descricao = dto.descricao();
    }

    public void preDados(Double soma){

        int indiceDeOrcamento = 0;
        
        if (this.orcamento.doubleValue() > soma / 1.5)
            indiceDeOrcamento = 3;
        if (this.orcamento.doubleValue() < soma / 1.5)
            indiceDeOrcamento = 2;
        if (this.orcamento.doubleValue() < soma / 2)
            indiceDeOrcamento = 1;
        
        this.risco = (0.4 * 4) + (0.3 * 3) + (0.2 * indiceDeOrcamento)
                + (0.1 * this.responsaveis.size()) + this.prioridade.getValue();
    }


}
