package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.departamento.DadosCadastroDepartamento;
import br.com.empresa.Empresa.domain.departamento.DadosDetalhamentoDepartamento;
import br.com.empresa.Empresa.domain.departamento.Departamento;
import br.com.empresa.Empresa.domain.departamento.DepartamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Validated DadosCadastroDepartamento dados,
                                    UriComponentsBuilder uriBuilder){
        var departamento = new Departamento(dados);
        repository.save(departamento);

        var uri = uriBuilder.path("/departamento/{id}").buildAndExpand(departamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoDepartamento(departamento));

    }
}
