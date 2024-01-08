package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.DadosDetalhamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.repository.ReuniaoRepository;
import br.com.empresa.Empresa.domain.reuniao.validacoes.ValidadorAgendarReuniao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private List<ValidadorAgendarReuniao> validadores;

    @Autowired
    private EmailService emailService;

    public Stream<DadosDetalhamentoReuniao> listar() {
        var lista = reuniaoRepository.findAll()
                .stream().map(DadosDetalhamentoReuniao::new);
        return lista;
    }

    public DadosDetalhamentoReuniao agendar(DadosAgendamentoReuniao dados) {

        validadores.forEach(v -> v.validar(dados));

        var listF = new LinkedList<Funcionario>();

        dados.funcionarios().forEach(f -> listF.add(funcionarioRepository.getReferenceById(f)));

        var reuniao = new Reuniao(dados, listF);

        listF.forEach(lf -> {
            lf.setReuniao(reuniao);
            emailService.enviarEmail(lf,"agendar");
        });

        reuniaoRepository.save(reuniao);

        return new DadosDetalhamentoReuniao(reuniao);
    }
}
