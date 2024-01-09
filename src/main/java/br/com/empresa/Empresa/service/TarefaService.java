package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import br.com.empresa.Empresa.domain.repository.TarefaRepository;
import br.com.empresa.Empresa.domain.tarefa.DadosCadastroTarefa;
import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Tarefa cadastro(DadosCadastroTarefa dadosCadastroTarefa) {

        var funcionarios = funcionarioRepository.findAllById(
                dadosCadastroTarefa.reponsaveis());

        var responsaveis = new HashSet<>(funcionarios);

        var tarefa = new Tarefa(dadosCadastroTarefa, responsaveis);

        tarefaRepository.save(tarefa);

        return tarefa;
    }

}
