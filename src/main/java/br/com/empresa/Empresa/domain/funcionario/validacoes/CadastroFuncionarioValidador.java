package br.com.empresa.Empresa.domain.funcionario.validacoes;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.funcionario.DadosCadastroFuncionario;
import br.com.empresa.Empresa.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroFuncionarioValidador implements ValidadorCadastroFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void validar(DadosCadastroFuncionario dados){

        var funcionarios = funcionarioRepository.findAllByStatusTrue();

        funcionarios.forEach( f -> {
            if (f.getCpf().equals(dados.cpf()))
                throw new ValidarException("CPF já cadastrado no sistema");
            if (f.getEmail().equals(dados.email()))
                throw new ValidarException("Email já registrado");
        });
    }
}
