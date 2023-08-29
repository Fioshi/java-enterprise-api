package br.com.empresa.Empresa.domain.funcionario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFuncionario(

        @NotNull
        Long id,

        String cpf,

        String nome,

        String sobrenome

) {
    public DadosAtualizacaoFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getCpf(), funcionario.getNome(), funcionario.getSobrenome());
    }
}
