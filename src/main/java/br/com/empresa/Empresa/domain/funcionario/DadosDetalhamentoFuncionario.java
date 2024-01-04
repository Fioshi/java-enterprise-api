package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.DadosDetalhamentoDepartamento;
import br.com.empresa.Empresa.domain.departamento.Departamento;
import br.com.empresa.Empresa.domain.departamento.TiposDepartamento;
import br.com.empresa.Empresa.domain.endereco.DadosDetalhamentoEndereco;
import br.com.empresa.Empresa.domain.endereco.Endereco;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.TiposReuniao;

import java.math.BigDecimal;

public record DadosDetalhamentoFuncionario(

        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        BigDecimal salario,
        String departamento,
        Boolean status,
        DadosDetalhamentoEndereco endereco

        ) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getSalario(),
                funcionario.getDepartamento().getNome().toString().toLowerCase(),
                funcionario.isStatus(),
                new DadosDetalhamentoEndereco(funcionario.getEndereco()));
    }

}
