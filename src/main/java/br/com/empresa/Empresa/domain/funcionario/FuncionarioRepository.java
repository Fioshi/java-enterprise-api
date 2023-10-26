package br.com.empresa.Empresa.domain.funcionario;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findAllByStatusTrue(Pageable paginacao);


    List<Funcionario> findByCpfContaining( String keyword);
}
