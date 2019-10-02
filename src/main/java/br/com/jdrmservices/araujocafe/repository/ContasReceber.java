package br.com.jdrmservices.araujocafe.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.ContaReceber;
import br.com.jdrmservices.araujocafe.repository.helper.contaReceber.ContasReceberQueries;

@Repository
public interface ContasReceber extends JpaRepository<ContaReceber, Long>, ContasReceberQueries {
	public Optional<ContaReceber> findByNumeroProcesso(String numeroProcesso);
	public List<ContaReceber> findByDataVencimento(LocalDate dataVencimento);	
}
