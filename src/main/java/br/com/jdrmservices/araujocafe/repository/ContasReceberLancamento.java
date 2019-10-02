package br.com.jdrmservices.araujocafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.ContaReceberLancamento;
import br.com.jdrmservices.araujocafe.repository.helper.contaReceberLancamento.ContasReceberLancamentoQueries;

@Repository
public interface ContasReceberLancamento extends JpaRepository<ContaReceberLancamento, Long>, ContasReceberLancamentoQueries {
	public Optional<ContaReceberLancamento> findByCodigo(Long codigo);
}
