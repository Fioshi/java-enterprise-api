package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findAllByStatusTrue(Pageable paginacao);

    List<Funcionario> findAllByStatusTrueOrderByNome();

    List<Funcionario> findByNomeContaining(String keyword);

    @Query("""
            SELECT f FROM Funcionario f WHERE f.departamento.nome = 'RH'
            """)
    List<Funcionario> findAllByDepartamentoRh();

}
