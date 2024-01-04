package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.service.EmailService;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.DadosDetalhamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.ReuniaoRepository;
import br.com.empresa.Empresa.domain.reuniao.validacoes.ValidadorAgendarReuniao;
import br.com.empresa.Empresa.service.ReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/reuniao")
public class ReuniaoController {

    @Autowired
    private ReuniaoService service;

    @GetMapping
    public ResponseEntity listar() {
        var lista = service.listar();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Validated DadosAgendamentoReuniao dados) {
        var reuniao = service.agendar(dados);
        return ResponseEntity.ok(reuniao);
    }
}