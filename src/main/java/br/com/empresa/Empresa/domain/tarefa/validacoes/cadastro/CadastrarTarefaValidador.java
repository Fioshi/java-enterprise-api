package br.com.empresa.Empresa.domain.tarefa.validacoes.cadastro;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.repository.TarefaRepository;
import br.com.empresa.Empresa.domain.tarefa.DadosCadastroTarefa;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CadastrarTarefaValidador implements ValidarCadastroTarefa {

    @Autowired
    private TarefaRepository repository;

    @Override
    public void validar(DadosCadastroTarefa dto) {

        if (dto.orcamento().compareTo(new BigDecimal(10000)) > 10000)
            throw new ValidarException("Orçamento ultrapassado");

        repository.findAllByPendenteAndAndamento().forEach( t -> {
            if (t.getNome() == dto.nome())
                throw new ValidarException("Já a uma tarefa com esse nome cadastrado");
        });
    }
}
