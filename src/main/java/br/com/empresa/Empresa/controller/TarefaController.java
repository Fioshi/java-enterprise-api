package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.tarefa.DadosAtualizacaoTarefa;
import br.com.empresa.Empresa.domain.tarefa.DadosCadastroTarefa;
import br.com.empresa.Empresa.domain.tarefa.DadosDetalhamentoTarefa;
import br.com.empresa.Empresa.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTarefa> cadastrar (@RequestBody @Valid DadosCadastroTarefa dadosCadastroTarefa,
                                                              UriComponentsBuilder builder){
        var tarefa = service.cadastro(dadosCadastroTarefa);

        var uri = builder.path("/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTarefa(tarefa));
    }

}

