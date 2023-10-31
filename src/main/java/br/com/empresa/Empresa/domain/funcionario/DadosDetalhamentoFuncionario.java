package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.DadosDetalhamentoDepartamento;
import br.com.empresa.Empresa.domain.departamento.Departamento;
import br.com.empresa.Empresa.domain.departamento.TiposDepartamento;

public record DadosDetalhamentoFuncionario(

        Long id,
        String nome,
        String sobrenome,
        String cpf,

        String email,
        String departamento,

        Boolean status
        ) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getDepartamento().getNome().toString().toLowerCase(),
                funcionario.isStatus());
    }

}
