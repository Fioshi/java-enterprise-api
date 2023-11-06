package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.departamento.DepartamentoRepository;
import br.com.empresa.Empresa.domain.funcionario.*;
import br.com.empresa.Empresa.domain.funcionario.validacoes.ValidadorCadastroFuncionario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private List<ValidadorCadastroFuncionario> validadores;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Validated DadosCadastroFuncionario dados,
                                    UriComponentsBuilder uriBuilder) {
        validadores.forEach(v -> v.validar(dados));

        var departamento = departamentoRepository.getReferenceById(dados.departamento());
        var funcionario = new Funcionario(dados, departamento);

        var uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();

        funcionarioRepository.save(funcionario);
        departamento.getFuncionarios().add(funcionario);

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.excluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados) {
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity<Stream<DadosDetalhamentoFuncionario>> listar(@PageableDefault(size = 5,
            sort = {"nome"}) Pageable pageable) {
        var page = funcionarioRepository.findAllByStatusTrue(pageable).stream()
                .map(DadosDetalhamentoFuncionario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/busca/all")
    public ResponseEntity buscaAll() {
        var funcionarios =
                funcionarioRepository.findAllByStatusTrueOrderByNome().stream().map(DadosDetalhamentoFuncionario::new);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/busca")
    public ResponseEntity buscarFiltrado(@RequestParam("keyword") String keyword) {
        var funcionario = funcionarioRepository.findByCpfContaining(keyword);
        List<DadosDetalhamentoFuncionario> list = new LinkedList<>();
        funcionario.forEach(f -> {
            list.add(new DadosDetalhamentoFuncionario(f));
        });
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }
}
