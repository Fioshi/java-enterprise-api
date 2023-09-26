package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.email.service.EmailService;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.DadosDetalhamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.ReuniaoRepository;
import br.com.empresa.Empresa.domain.reuniao.validacoes.AgendarValidador;
import br.com.empresa.Empresa.domain.reuniao.validacoes.ValidadorAgendarReuniao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("reuniao")
public class ReuniaoController {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private List<ValidadorAgendarReuniao> validadores;

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Validated DadosAgendamentoReuniao dados) {

        var list = dados.funcionarios();
        var listF = new LinkedList<Funcionario>();

        validadores.forEach(v -> v.validar(dados));

        for (Long l :
                list) {
            listF.add(funcionarioRepository.getReferenceById(l));
        }
        var reuniao = new Reuniao(dados, listF);

        for (Funcionario f:
                listF) {
            f.setReuniao(reuniao);
            emailService.enviarEmailReuniao(f);
        }

        reuniaoRepository.save(reuniao);

        return ResponseEntity.ok(new DadosDetalhamentoReuniao(reuniao));
    }



}
