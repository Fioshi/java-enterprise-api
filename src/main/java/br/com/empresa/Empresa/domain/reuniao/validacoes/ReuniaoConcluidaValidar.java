package br.com.empresa.Empresa.domain.reuniao.validacoes;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ReuniaoConcluidaValidar {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Scheduled(fixedDelay = 50000)
    @Transactional
    public void retirar() {

        var agora = LocalDateTime.now();
        var reuniaoList = reuniaoRepository.findAllByStatusTrue();
        var funcionarioList = funcionarioRepository.findAll();

        for (Reuniao r :
                reuniaoList) {
            System.out.println(Duration.between(agora, r.getHorario()).toMinutes());
            if (Duration.between(agora, r.getHorario()).toMinutes() <= 0) {
                for (Funcionario f:
                     funcionarioList) {
                    if (f.getReuniao().equals(r)){
                        f.reuniaoConcluida();
                        r.excluir();
                    }
                }
            }
        }
    }
}
