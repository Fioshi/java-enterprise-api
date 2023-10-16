package br.com.empresa.Empresa.domain.funcionario.validacoes;

import br.com.empresa.Empresa.domain.ValidarException;
import br.com.empresa.Empresa.domain.funcionario.DadosCadastroFuncionario;
import org.springframework.stereotype.Component;

@Component
public class CpfValidador implements ValidadorCadastroFuncionario {
    public void validar(DadosCadastroFuncionario dados) {
        // Remove caracteres não numéricos do CPF
        // var cpf = funcionario.cpf().replaceAll("[^0-9]", "");
        var cpf = dados.cpf();

        // Verifique se o CPF tem 11 dígitos
        if (cpf.length() != 11)
            throw new ValidarException("CPF informado não contêm 11 digitos");


        // Verifique se todos os dígitos são iguais (CPF inválido, mas estruturalmente correto)
        if (cpf.matches("(\\d)\\1{10}"))
            throw new ValidarException("CPF possui todos os digitos iguais");


        // Calcula os dígitos verificadores
        int[] numerosCPF = new int[11];
        for (int i = 0; i < 11; i++) {
            numerosCPF[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma1 = 0;
        int soma2 = 0;

        for (int i = 0; i < 9; i++) {
            soma1 += numerosCPF[i] * (10 - i);
            soma2 += numerosCPF[i] * (11 - i);
        }

        int digito1 = 11 - (soma1 % 11);
        if (digito1 >= 10)
            digito1 = 0;


        soma2 += digito1 * 2;
        int digito2 = 11 - (soma2 % 11);
        if (digito2 >= 10)
            digito2 = 0;


        // Verifique os dígitos verificadores
        if (digito1 != numerosCPF[9] && digito2 != numerosCPF[10])
            throw new ValidarException("CPF invalido");
    }
}
