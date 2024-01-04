package br.com.empresa.Empresa.domain.reuniao.validacoes;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;

@Component
public class AgendarValidador implements ValidadorAgendarReuniao{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Override
    public void validar(DadosAgendamentoReuniao dados) {
        var agora = LocalDateTime.now();
        var list = funcionarioRepository.findAllById(dados.funcionarios());
        var listR = reuniaoRepository.findAllByStatusTrue();

        listR.forEach( r -> {
            if (Duration.between(dados.horario(), r.getHorario()).toHours() == 1 || Duration.between(dados.horario(),
                    r.getHorario()).toHours() == 0)
                throw new ValidarException("Já há uma reunião proxima desse horario, o tempo entre as reuniões é de 1" +
                        " hora");
        });

        list.forEach( l -> {
            if (l.getReuniao() != null)
                throw new ValidarException("Funcionario já com reunião marcada");
        });

        if (Duration.between(agora, dados.horario()).toDays() <= 1)
            throw new ValidarException("Só é possivel marcar reuniões para no minimo daqui a 2 dias");
    }
}
