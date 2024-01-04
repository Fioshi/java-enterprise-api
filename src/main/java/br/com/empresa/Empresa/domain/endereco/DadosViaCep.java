package br.com.empresa.Empresa.domain.endereco;

public record DadosViaCep(

        String logradouro,
        String bairro,
        String localidade,
        String uf,
        String cep

) {
}
