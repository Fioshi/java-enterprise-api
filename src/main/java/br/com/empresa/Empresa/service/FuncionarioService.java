package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.departamento.DepartamentoRepository;
import br.com.empresa.Empresa.domain.endereco.DadosViaCep;
import br.com.empresa.Empresa.domain.endereco.Endereco;
import br.com.empresa.Empresa.domain.endereco.EnderecoRepository;
import br.com.empresa.Empresa.domain.funcionario.*;
import br.com.empresa.Empresa.domain.funcionario.validacoes.ValidadorCadastroFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private List<ValidadorCadastroFuncionario> validadores;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private RestTemplate template;

    public Funcionario cadastro(DadosCadastroFuncionario dados) {

        validadores.forEach(v -> v.validar(dados));

        var departamento = departamentoRepository.getReferenceById(dados.departamento());

        var url = "https://viacep.com.br/ws/" + dados.endereco().cep() + "/json/";

        DadosViaCep dadosViaCep = template.getForObject(url, DadosViaCep.class);

        var endereco = new Endereco(dados.endereco(), dadosViaCep);
        var funcionario = new Funcionario(dados, departamento, endereco);

        funcionarioRepository.save(funcionario);
        enderecoRepository.save(endereco);

        departamento.getFuncionarios().add(funcionario);

        
        return funcionario;
    }

    public void excluir(Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.excluir();
    }

    public Funcionario atualizar(DadosAtualizacaoFuncionario dados) {
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizar(dados);
        return funcionario;
    }

    public Stream<DadosDetalhamentoFuncionario> listar(@PageableDefault(size = 5,
            sort = {"nome"}) Pageable pageable) {
        return funcionarioRepository.findAllByStatusTrue(pageable).stream()
                .map(DadosDetalhamentoFuncionario::new);
    }

    public Stream<DadosDetalhamentoFuncionario> buscaAll() {
        return funcionarioRepository.findAllByStatusTrueOrderByNome().stream()
                .map(DadosDetalhamentoFuncionario::new);
    }

    public Stream<DadosDetalhamentoFuncionario> buscaFiltrada(String keyword) {
        return funcionarioRepository.findByNomeContaining(keyword).stream()
                .map(DadosDetalhamentoFuncionario::new);

    }

    public Funcionario busca (Long id){
        return funcionarioRepository.getReferenceById(id);
    }
}
