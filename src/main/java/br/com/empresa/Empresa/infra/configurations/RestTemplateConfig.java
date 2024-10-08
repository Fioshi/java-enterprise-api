package br.com.empresa.Empresa.infra.configurations;

import br.com.empresa.Empresa.domain.tarefa.validacoes.cadastro.ValidarCadastroTarefa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restCriar(){
        return new RestTemplate();
    }

    @Bean
    public List<ValidarCadastroTarefa> validadores(){
        return new ArrayList<ValidarCadastroTarefa>();
    }
    
}
