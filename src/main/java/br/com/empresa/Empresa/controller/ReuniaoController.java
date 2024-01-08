package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.service.ReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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