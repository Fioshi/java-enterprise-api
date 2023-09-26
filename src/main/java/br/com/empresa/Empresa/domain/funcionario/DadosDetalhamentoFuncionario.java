package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.DadosDetalhamentoDepartamento;
import br.com.empresa.Empresa.domain.departamento.Departamento;
import br.com.empresa.Empresa.domain.departamento.TiposDepartamento;

public record DadosDetalhamentoFuncionario(
        String nome,
        String sobrenome,
        String cpf,

        String email,
        TiposDepartamento departamento) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getDepartamento().getNome());
    }

}
