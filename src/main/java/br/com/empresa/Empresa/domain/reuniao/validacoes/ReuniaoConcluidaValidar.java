package br.com.empresa.Empresa.domain.reuniao.validacoes;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.ReuniaoRepository;
import br.com.empresa.Empresa.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Component
public class ReuniaoConcluidaValidar {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Transactional
    public void retirar() {

        var agora = LocalDateTime.now();
        var reuniaoList = reuniaoRepository.findAllByStatusTrue();

        reuniaoList.forEach(r -> {

            r.getFuncionarios().forEach( f -> {
                if (Duration.between(agora, r.getHorario()).toDays() <= 1) {
                    System.out.println(Duration.between(agora, r.getHorario()).toDays());
                    emailService.enviarEmail(f, "lembrete");
                }
            });

            if (Duration.between(agora, r.getHorario()).toMinutes() <= 0) {
                r.excluir();
                r.getFuncionarios().forEach(Funcionario::reuniaoConcluida);
            }
        });
    }
}