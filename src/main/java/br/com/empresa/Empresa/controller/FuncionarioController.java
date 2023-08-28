package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.departamento.DepartamentoRepository;
import br.com.empresa.Empresa.domain.funcionario.DadosCadastroFuncionario;
import br.com.empresa.Empresa.domain.funcionario.DadosDetalhamentoFuncionario;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Validated DadosCadastroFuncionario dados,
                                    UriComponentsBuilder uriBuilder){

        var departamento = departamentoRepository.getReferenceById(dados.departamento());
        var funcionario = new Funcionario(dados,departamento);
        departamento.getFuncionarios().add(funcionario);

        var uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();

        funcionarioRepository.save(funcionario);

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

}
