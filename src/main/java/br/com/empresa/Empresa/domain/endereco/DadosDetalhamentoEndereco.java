package br.com.empresa.Empresa.domain.endereco;

public record DadosDetalhamentoEndereco(

        String logradouro,

        int numero,

        String complemento,

        String bairro,

        String localidade,

        String uf,

        String cep

) {
    public DadosDetalhamentoEndereco (Endereco endereco){
        this(
                endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(),
                endereco.getLocalidade(), endereco.getUf(), endereco.getCep());
    }
}
