package br.com.empresa.Empresa.domain.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFuncionario(

        Long id,

        String nome,

        String sobrenome,

        String cpf,

        String email

) {
//    public DadosAtualizacaoFuncionario(Funcionario funcionario) {
//        this(funcionario.getCpf(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getEmail());
//    }
}
