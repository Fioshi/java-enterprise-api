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

        return
        "<div>" +
        "<p style=>Prezado(a) "+ f.getNome() +",</p>" +
        "<p>Espero que esta mensagem o encontre bem. Gostaria de convidá-lo(a) para uma reunião importante agendada para: </p>" +
        "<p>Data da Reunião: " + dia + "/" + mes + " Horario: " + hora + ":" + minuto + "</p>" +
        "<p>Motivo da Reunião: <em>"+ motivo.toString().toLowerCase() +"</em></p>" +
        "<p>Por favor, confirme sua disponibilidade para esta reunião o mais breve possível.</p>" +
        "<p>Se você tiver alguma dúvida ou precisar de mais informações, não hesite em entrar em contato conosco.</p>" +
        "<p>Estou ansioso(a) para discutir com você e nossa equipe.</p>" +
        "<p>Obrigado(a) e cumprimentos,</p>" +
        "<p>EmpresaAPI</p>" +
        "</div>";
    }
}
