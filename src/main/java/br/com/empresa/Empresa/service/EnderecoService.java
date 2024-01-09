package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.endereco.DadosViaCep;
import br.com.empresa.Empresa.domain.endereco.Endereco;
import br.com.empresa.Empresa.domain.funcionario.DadosCadastroFuncionario;
import br.com.empresa.Empresa.domain.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    @Autowired
    private RestTemplate template;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco cadastroEndereco(DadosCadastroFuncionario dados){

        var url = "https://viacep.com.br/ws/" + dados.endereco().cep() + "/json/";

        DadosViaCep dadosViaCep = template.getForObject(url, DadosViaCep.class);

        var endereco = new Endereco(dados.endereco(), dadosViaCep);

        enderecoRepository.save(endereco);

        return endereco;
    }
}
