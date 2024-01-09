package br.com.empresa.Empresa.domain.tarefa;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public record DadosDetalhamentoTarefa(

        String nome,

        Prioridade prioridade,

        Stream<String> reponsaveis,

        Estado estado,

        BigDecimal orcamento,

        String descricao

) {
    public DadosDetalhamentoTarefa(Tarefa tarefa) {
        this(
                tarefa.getNome(),
                tarefa.getPrioridade(),
                tarefa.getResponsaveis().stream().map(Funcionario::getNome),
                tarefa.getEstado(),
                tarefa.getOrcamento(),
                tarefa.getDescricao());
    }
}
