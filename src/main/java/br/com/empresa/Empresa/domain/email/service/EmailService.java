package br.com.empresa.Empresa.domain.email.service;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${support.mail}")
    private String supportMail;

    @Async
    @Transactional
    public void enviarEmailReuniao(Funcionario f) {

        var mensagem = gerarMensagem(f);
        var subject = "Reunião";

        MimeMessage mail = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mail);

        try {
            helper.setFrom(supportMail);
            helper.setTo(f.getEmail());
            helper.setSubject(subject);
            helper.setText(mensagem, true);

            emailSender.send(mail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String gerarMensagem(Funcionario f) {
        var dia = f.getReuniao().getHorario().getDayOfMonth();
        var hora = f.getReuniao().getHorario().getHour();
        var minuto = f.getReuniao().getHorario().getMinute();
        var mes = f.getReuniao().getHorario().getMonthValue();
        var motivo = f.getReuniao().getTipo();

        return "<h1>Reunião Marcada &#128172;</h1>" +
                "<p> Olá <b>" + f.getNome() + "</b> você está incluido em uma " +
                "reunião " +
                "que acontecera as <b>" + hora + ":" + minuto + "</b> do dia <b>" + dia + "/" + mes + "</b>" +
                "<p><b>Motivo:</b> " + motivo.toString().toLowerCase() + "</p>";
    }
}
