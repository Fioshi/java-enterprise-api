package br.com.empresa.Empresa.service;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.historico.Historico;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import br.com.empresa.Empresa.domain.repository.HistoricoRepository;
import br.com.empresa.Empresa.domain.repository.TarefaRepository;
import br.com.empresa.Empresa.domain.tarefa.DadosAtualizacaoTarefa;
import br.com.empresa.Empresa.domain.tarefa.DadosCadastroTarefa;
import br.com.empresa.Empresa.domain.tarefa.Estado;
import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import br.com.empresa.Empresa.domain.tarefa.validacoes.cadastro.ValidarCadastroTarefa;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private List<ValidarCadastroTarefa> validadores;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Tarefa cadastro(DadosCadastroTarefa dadosCadastroTarefa) {

        validadores.forEach(v -> v.validar(dadosCadastroTarefa));

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

    public Tarefa atualizar(DadosAtualizacaoTarefa dto) {

        var tarefa = tarefaRepository.getReferenceById(dto.id());

        if (tarefa.getEstado() == Estado.CONCLUIDO)
            throw new ValidarException("Não é possivel fazer alterações em tarefas concluidas");

        tarefa.atualizar(dto);

        var historico = new Historico(tarefa);

        tarefa.getHistorico().add(historico);

        historicoRepository.save(historico);

        return tarefa;
    }
}
