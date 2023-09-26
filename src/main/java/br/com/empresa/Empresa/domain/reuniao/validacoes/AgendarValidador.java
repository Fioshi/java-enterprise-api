package br.com.empresa.Empresa.domain.reuniao.validacoes;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import br.com.empresa.Empresa.domain.reuniao.DadosAgendamentoReuniao;
import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import br.com.empresa.Empresa.domain.reuniao.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class AgendarValidador implements ValidadorAgendarReuniao{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Override
    public void validar(DadosAgendamentoReuniao dados) {

    }
}
