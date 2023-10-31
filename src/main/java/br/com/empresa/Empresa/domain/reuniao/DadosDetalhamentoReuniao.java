package br.com.empresa.Empresa.domain.reuniao;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoReuniao(Long id, TiposReuniao tipo,
                                       LocalDateTime horario, Boolean status) {

    public DadosDetalhamentoReuniao(Reuniao reuniao){
        this(reuniao.getId() ,reuniao.getTipo(),
                reuniao.getHorario(), reuniao.isStatus());
    }
}
