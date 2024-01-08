package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.repository.DepartamentoRepository;
import br.com.empresa.Empresa.domain.endereco.DadosViaCep;
import br.com.empresa.Empresa.domain.endereco.Endereco;
import br.com.empresa.Empresa.domain.repository.EnderecoRepository;
import br.com.empresa.Empresa.domain.funcionario.*;
import br.com.empresa.Empresa.domain.funcionario.validacoes.ValidadorCadastroFuncionario;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private List<ValidadorCadastroFuncionario> validadores;

    public Funcionario cadastro(DadosCadastroFuncionario dados) {

        validadores.forEach(v -> v.validar(dados));

        var endereco = enderecoService.cadastroEndereco(dados);

        var departamento = departamentoRepository.getReferenceById(dados.departamento());

        var funcionario = new Funcionario(dados, departamento, endereco);

        departamento.getFuncionarios().add(funcionario);

        funcionarioRepository.save(funcionario);

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
