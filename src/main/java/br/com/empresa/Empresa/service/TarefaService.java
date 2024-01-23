package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.historico.Historico;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import br.com.empresa.Empresa.domain.repository.HistoricoRepository;
import br.com.empresa.Empresa.domain.repository.TarefaRepository;
import br.com.empresa.Empresa.domain.tarefa.DadosCadastroTarefa;
import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Tarefa cadastro(DadosCadastroTarefa dadosCadastroTarefa) {

        var funcionarios = funcionarioRepository.findAllById(
                dadosCadastroTarefa.reponsaveis());

        var responsaveis = new HashSet<>(funcionarios);

        var tarefa = new Tarefa(dadosCadastroTarefa, responsaveis);
        var historico = new Historico(tarefa);
        tarefa.getHistorico().add(historico);

        tarefaRepository.save(tarefa);
        historicoRepository.save(historico);

        return tarefa;
    }

    public Tarefa atualizar() {
        return new Tarefa();
    }
}
