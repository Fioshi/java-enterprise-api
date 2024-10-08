package br.com.empresa.Empresa.domain.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTarefa(

        @NotNull
        Long id,

        @NotBlank
        String descricao

) {
}