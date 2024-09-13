package br.com.empresa.Empresa.domain.reuniao;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tb_reuniao")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "reuniao")
    private List<Funcionario> funcionarios;

    private LocalDateTime horario;

    @Enumerated(EnumType.STRING)
    private TiposReuniao tipo;

    private boolean status;

    private String mensagem;

    public Reuniao(DadosAgendamentoReuniao dados, LinkedList<Funcionario> listF) {
        this.status = true;
        this.horario = dados.horario();
        this.tipo = dados.tipo();
        this.mensagem = dados.mensagem();
        this.funcionarios = listF;
    }

    public void excluir() {
        this.status = false;
    }
}
