package br.com.empresa.Empresa.service;

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
    public void enviarEmail(Funcionario f, String tipoEmail) {

        String mensagem = "";

        if (tipoEmail.equals("lembrete"))
            mensagem = gerarMensagemLembrete(f);

        if (tipoEmail.equals("agendar"))
            mensagem = gerarMensagemReuniao(f);


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

    public String gerarMensagemLembrete(Funcionario f) {
        var dia = f.getReuniao().getHorario().getDayOfMonth();
        var hora = f.getReuniao().getHorario().getHour();
        var minuto = f.getReuniao().getHorario().getMinute();
        var mes = f.getReuniao().getHorario().getMonthValue();
        var motivo = f.getReuniao().getTipo();

        return
                "<p ><strong > Assunto:</strong > Lembrete de Reunião - [Título da Reunião]</p >" +

                        "<p > Prezado " + f.getNome() + ",</p >" +
                        "<p > Espero que esta mensagem o encontre bem.Este é um lembrete amigável sobre a nossa próxima reunião" +
                        "agendada.Abaixo estão os detalhes:</p >" +

                        "<ul >" +
                        "<li ><strong > Data:</strong > " + dia +"/"+ mes + " </li >"+
                        "<li ><strong > Hora:</strong > " + hora + ":" + minuto + " </li >"+
                        "<li ><strong > Local / Virtual:</strong >Teams</li >"+
                        "</ul >" +

                        "<p ><strong > Objetivo da Reunião:</strong ><br >" +
                        motivo + "</p>" +

                        "<p > Por favor, esteja preparado para discutir e traga"+
                        "quaisquer documentos ou informações relevantes para a discussão.</p >"+

                        "<p > Se houver alguma mudança em sua disponibilidade ou se precisar abordar algum ponto específico durante"+
                        "a reunião, por favor, avise com antecedência.</p >"+

                        "<p > Agradecemos sua participação e contribuição para o sucesso desta reunião.Estamos ansiosos para nos " +
                        "conectar e" +
                        "colaborar.< / p >";
    }

    public String gerarMensagemReuniao(Funcionario f) {
        var dia = f.getReuniao().getHorario().getDayOfMonth();
        var hora = f.getReuniao().getHorario().getHour();
        var minuto = f.getReuniao().getHorario().getMinute();
        var mes = f.getReuniao().getHorario().getMonthValue();
        var motivo = f.getReuniao().getTipo();

        return
                "<div>" +
                        "<p>Prezado(a) " + f.getNome() + ",</p>" +
                        "<p>Espero que esta mensagem o encontre bem. Gostaria de convidá-lo(a) para uma reunião importante agendada para: </p>" +
                        "<p>Data da Reunião: " + dia + "/" + mes + " Horario: " + hora + ":" + minuto + "</p>" +
                        "<p>Motivo da Reunião: <em>" + motivo.toString().toLowerCase() + "</em></p>" +
                        "<p>Por favor, confirme sua disponibilidade para esta reunião o mais breve possível.</p>" +
                        "<p>Se você tiver alguma dúvida ou precisar de mais informações, não hesite em entrar em contato conosco.</p>" +
                        "<p>Estou ansioso(a) para discutir com você e nossa equipe.</p>" +
                        "<p>Obrigado(a) e cumprimentos,</p>" +
                        "<p>EmpresaAPI</p>" +
                        "</div>";
    }

}
