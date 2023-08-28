package br.com.empresa.Empresa.domain.funcionario;

import br.com.empresa.Empresa.domain.departamento.Departamento;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,

        @NotBlank
        String sobrenome,

        @NotBlank
        String cpf,

        Long departamento
    ) {
}
