package br.com.empresa.Empresa.domain.reuniao.validacoes;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.domain.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AgendarValidador implements ValidadorAgendarReuniao{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Override
    public void validar(DadosAgendamentoReuniao dados) {

        reuniaoRepository.findAllByStatusTrue().forEach( r -> {
            if (Duration.between(dados.horario(), r.getHorario()).toHours() == 1 || Duration.between(dados.horario(),
                    r.getHorario()).toHours() == 0)
                throw new ValidarException("Já há uma reunião proxima desse horario, o tempo entre as reuniões é de 1" +
                        " hora");
        });

        funcionarioRepository.findAllById(dados.funcionarios()).forEach( l -> {
            if (l.getReuniao() != null)
                throw new ValidarException("Funcionario já com reunião marcada");
        });

        if (Duration.between(LocalDateTime.now(), dados.horario()).toDays() <= 1)
            throw new ValidarException("Só é possivel marcar reuniões para no minimo daqui a 2 dias");
    }
}
