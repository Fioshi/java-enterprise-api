package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT t FROM Tarefa t where t.estado = PENDENTE and t.estado = ANDAMENTO")
    List<Tarefa> findAllByPendenteAndAndamento();

    @Query("SELECT sum(t.orcamento) FROM Tarefa t")
    Double somaOrcamento();
}
