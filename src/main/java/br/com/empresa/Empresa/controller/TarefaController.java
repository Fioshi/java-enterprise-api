package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.tarefa.DadosCadastroTarefa;
import br.com.empresa.Empresa.domain.tarefa.DadosDetalhamentoTarefa;
import br.com.empresa.Empresa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTarefa> cadastrar (@RequestBody @Validated DadosCadastroTarefa dadosCadastroTarefa,
                                                              UriComponentsBuilder builder){
        var tarefa = service.cadastro(dadosCadastroTarefa);
        var uri = builder.path("/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTarefa(tarefa));
    }
}
