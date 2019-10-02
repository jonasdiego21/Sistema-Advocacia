package br.com.jdrmservices.araujocafe.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.ContaPagarLancamento;
import br.com.jdrmservices.araujocafe.repository.helper.contaPagarLancamento.ContasPagarLancamentoQueries;

@Repository
public interface ContasPagarLancamento extends JpaRepository<ContaPagarLancamento, Long>, ContasPagarLancamentoQueries {
	public Optional<ContaPagarLancamento> findByCodigo(Long codigo);
	public List<ContaPagarLancamento> findByDataPagamento(LocalDate dataPagamento);
}
