package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.Departamento;
import br.com.empresa.Empresa.domain.endereco.DadosCadastroEndereco;
import br.com.empresa.Empresa.domain.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,

        @NotBlank
        String sobrenome,

        @NotBlank
        String cpf,

        @NotNull
        BigDecimal salario,

        @NotBlank
        String email,

        @NotNull
        Long departamento,

        DadosCadastroEndereco endereco
    ) {
}
