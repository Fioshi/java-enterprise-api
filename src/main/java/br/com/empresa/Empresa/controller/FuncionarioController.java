package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.funcionario.*;
import br.com.empresa.Empresa.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrar(@RequestBody @Validated DadosCadastroFuncionario dados,
                                                                  UriComponentsBuilder uriBuilder) {
        var funcionario = service.cadastro(dados);
        var uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.ok("Exclusão concluida com sucesso");
        } catch (ValidarException erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados) {
        var funcionario = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));

    }

    @GetMapping
    public ResponseEntity<Stream<DadosDetalhamentoFuncionario>> listar(@PageableDefault(size = 5,
            sort = {"nome"}) Pageable pageable) {
        var page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/busca/all")
    public ResponseEntity<Stream<DadosDetalhamentoFuncionario>> buscaAll() {
        var funcionarios = service.buscaAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/busca")
    public ResponseEntity<Stream<DadosDetalhamentoFuncionario>> buscarFiltrado(@RequestParam("keyword") String keyword) {
        var funcionarios = service.buscaFiltrada(keyword);
        return ResponseEntity.ok(funcionarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFuncionario> buscar(@PathVariable Long id) {
        var funcionario = service.busca(id);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }
}
