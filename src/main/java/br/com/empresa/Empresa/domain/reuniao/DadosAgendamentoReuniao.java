package br.com.empresa.Empresa.domain.reuniao;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DadosAgendamentoReuniao(

        @NotNull
        List<Long> funcionarios,

        @Future
        @NotNull
        LocalDateTime horario,

        @NotNull
        TiposReuniao tipo

) {
}
