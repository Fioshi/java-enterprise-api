package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.historico.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
