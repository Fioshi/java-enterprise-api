package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.historico.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
//    @Query("""
//            SELECT h FROM Historico h WHERE h.tarefa.id = :id
//            """)
    public Historico findByTarefaId(Long id);
}
