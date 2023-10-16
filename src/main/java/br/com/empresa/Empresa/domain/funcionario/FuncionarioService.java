package br.com.empresa.Empresa.domain.funcionario;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    public String formatarCpf(String cpf){
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9);
    }

}
