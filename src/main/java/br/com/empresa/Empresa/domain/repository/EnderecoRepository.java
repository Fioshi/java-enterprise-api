package br.com.empresa.Empresa.domain.repository;

import br.com.empresa.Empresa.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
