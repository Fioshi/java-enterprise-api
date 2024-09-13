package br.com.empresa.Empresa.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoEndereco(

        @NotBlank
        String logradouro,

        @NotNull
        int numero,

        @NotBlank
        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String localidade,

        @NotBlank
        String uf,

        @NotBlank

        String cep
) {
    public DadosDetalhamentoEndereco (Endereco endereco){
        this(
                endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(),
                endereco.getLocalidade(), endereco.getUf(), endereco.getCep());
    }
}
