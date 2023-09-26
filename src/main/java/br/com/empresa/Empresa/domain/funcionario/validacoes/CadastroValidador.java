package br.com.empresa.Empresa.domain.funcionario.validacoes;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.funcionario.DadosCadastroFuncionario;
import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import br.com.empresa.Empresa.domain.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CadastroValidador implements ValidadorCadastroFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void validar(DadosCadastroFuncionario dados){
        for (Funcionario f:
             funcionarioRepository.findAll()) {
            if (f.getCpf().equals(dados.cpf()))
                throw new ValidarException("CPF já cadastrado no sistema");
        }
    }

}
