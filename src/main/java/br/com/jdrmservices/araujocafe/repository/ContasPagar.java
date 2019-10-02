package br.com.jdrmservices.araujocafe.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.ContaPagar;
import br.com.jdrmservices.araujocafe.repository.helper.contaPagar.ContasPagarQueries;

@Repository
public interface ContasPagar extends JpaRepository<ContaPagar, Long>, ContasPagarQueries {
	public Optional<ContaPagar> findByFornecedorIgnoreCase(String fornecedor);	
	public List<ContaPagar> findByDataVencimento(LocalDate dataVencimento);
}
