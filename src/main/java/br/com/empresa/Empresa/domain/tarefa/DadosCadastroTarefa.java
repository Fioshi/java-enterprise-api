package br.com.empresa.Empresa.domain.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record DadosCadastroTarefa(

        @NotBlank
        String nome,

        @NotNull
        Prioridade prioridade,

        @NotNull
        List<Long> reponsaveis,


        @NotNull
        BigDecimal orcamento,

        @NotBlank
        String descricao,

        @NotNull
        LocalDate data
) {
}
