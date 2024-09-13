package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.departamento.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}