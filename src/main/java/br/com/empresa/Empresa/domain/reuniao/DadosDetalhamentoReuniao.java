package br.com.empresa.Empresa.domain.reuniao;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoReuniao(
        Long id,
        TiposReuniao tipo,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime horario,
        Boolean status) {

    public DadosDetalhamentoReuniao(Reuniao reuniao){
        this(reuniao.getId() ,reuniao.getTipo(),
                reuniao.getHorario(), reuniao.isStatus());
    }
}
