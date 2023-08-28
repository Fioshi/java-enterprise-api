package br.com.empresa.Empresa.domain.reuniao;

import java.time.LocalDateTime;

public record DadosDetalhamentoReuniao(Long id, TiposReuniao tipo, LocalDateTime date) {

    public DadosDetalhamentoReuniao(Reuniao reuniao){
        this(reuniao.getId(), reuniao.getTipo(), reuniao.getHorario());
    }
}
