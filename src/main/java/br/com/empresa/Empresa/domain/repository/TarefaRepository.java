package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
