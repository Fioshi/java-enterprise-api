package br.com.empresa.Empresa.domain.tarefa.validacoes.atualizacao;

import br.com.empresa.Empresa.domain.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AtualizarComplexidade {

    @Autowired
    public TarefaRepository repository;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Transactional
    public void atualizarComplexidade(){

        var soma = repository.somaOrcamento();
        var tarefas = repository.findAll();

        tarefas.forEach( t -> {
            t.preDados(soma);
        });
    }
}
