package br.com.empresa.Empresa.domain.departamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDepartamento(
        Long id,
        @NotNull
        TiposDepartamento nome

    ) {
}
