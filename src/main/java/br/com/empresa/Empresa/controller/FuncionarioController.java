package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.departamento.DepartamentoRepository;
import br.com.empresa.Empresa.domain.funcionario.DadosCadastroFuncionario;
import br.com.empresa.Empresa.domain.funcionario.DadosDetalhamentoFuncionario;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.funcionario.DadosAtualizacaoFuncionario;
import br.com.empresa.Empresa.domain.funcionario.validacoes.ValidadorCadastroFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private List<ValidadorCadastroFuncionario> validadores;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Validated DadosCadastroFuncionario dados,
                                    UriComponentsBuilder uriBuilder) {

        validadores.forEach(v->v.validar(dados));

        var departamento = departamentoRepository.getReferenceById(dados.departamento());
        var funcionario = new Funcionario(dados, departamento);
        departamento.getFuncionarios().add(funcionario);

        var uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();

        funcionarioRepository.save(funcionario);

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
    public ResponseEntity atualizar(DadosAtualizacaoFuncionario dados){
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizar(dados);
        return ResponseEntity.ok(new DadosAtualizacaoFuncionario(funcionario));
    }
}
