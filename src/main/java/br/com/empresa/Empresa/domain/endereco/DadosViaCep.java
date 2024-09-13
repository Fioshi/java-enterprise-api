package br.com.empresa.Empresa.domain.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosViaCep(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String localidade,

        @NotBlank
        String uf,

        @NotBlank
        String cep

) {
}
