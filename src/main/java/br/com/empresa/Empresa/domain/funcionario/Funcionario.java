package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.Departamento;
import br.com.empresa.Empresa.domain.endereco.Endereco;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_funcionario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_reuniao")
    private Reuniao reuniao;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToMany(mappedBy = "responsaveis")
    private Set<Tarefa> tarefas;

    private boolean status;

    public void excluir() {
        this.status = false;
    }

    public void reuniaoConcluida() {
        this.reuniao = null;
    }

    public void atualizar(DadosAtualizacaoFuncionario dados) {
        if (dados.cpf() != null)
            this.cpf = dados.cpf();
        if (dados.nome() != null)
            this.nome = dados.nome();
        if (dados.sobrenome() != null)
            this.sobrenome = dados.sobrenome();
        if (dados.email() != null)
            this.email = dados.email();
    }

    public Funcionario(DadosCadastroFuncionario dados, Departamento departamento, Endereco endereco) {
        this.nome = dados.nome();
        this.sobrenome = dados.sobrenome();
        this.cpf = dados.cpf();
        this.departamento = departamento;
        this.email = dados.email();
        this.status = true;
        this.endereco = endereco;
        this.salario = dados.salario();
    }
}

