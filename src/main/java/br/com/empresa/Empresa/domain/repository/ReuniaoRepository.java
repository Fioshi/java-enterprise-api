package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.reuniao.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {
    List<Reuniao> findAllByStatusTrue();
}
