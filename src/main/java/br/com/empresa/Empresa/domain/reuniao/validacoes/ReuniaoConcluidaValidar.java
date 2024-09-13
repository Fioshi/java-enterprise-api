package br.com.empresa.Empresa.domain.reuniao.validacoes;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import br.com.empresa.Empresa.domain.repository.ReuniaoRepository;
import br.com.empresa.Empresa.service.EmailService;
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

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Transactional
    public void retirar() {

        reuniaoRepository.findAllByStatusTrue().forEach(r -> {

            r.getFuncionarios().forEach( f -> {
                if (Duration.between(LocalDateTime.now(), r.getHorario()).toDays() <= 1) {
                    System.out.println(Duration.between(LocalDateTime.now(), r.getHorario()).toDays());
                    emailService.enviarEmail(f, "lembrete");
                }
            });

            if (Duration.between(LocalDateTime.now(), r.getHorario()).toMinutes() <= 0) {
                r.excluir();
                r.getFuncionarios().forEach(Funcionario::reuniaoConcluida);
            }
        });
    }
}