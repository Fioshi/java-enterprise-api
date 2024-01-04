package br.com.empresa.Empresa.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEndereco(

        @NotBlank
        String cep,

        @NotBlank
        String complemento,

        @NotNull
        int numero

) {
}
