package br.com.empresa.Empresa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

}