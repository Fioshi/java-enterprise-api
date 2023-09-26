package br.com.empresa.Empresa.domain.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedList;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
