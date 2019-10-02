package br.com.jdrmservices.araujocafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Despesa;

@Repository
public interface Despesas extends JpaRepository<Despesa, Long> {
	Despesa findOne(Long codigo);
	Optional<Despesa> findByDescricao(String descricao);
}
