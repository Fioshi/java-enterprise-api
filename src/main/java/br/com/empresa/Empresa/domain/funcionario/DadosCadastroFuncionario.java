package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.Departamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,

        @NotBlank
        String sobrenome,

        @NotBlank
        @Pattern(regexp = "\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b\n")
        String cpf,

        @NotBlank
        String email,

        @NotNull
        Long departamento
    ) {
}
