package br.com.jdrmservices.araujocafe.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.repository.Agendas;
import br.com.jdrmservices.araujocafe.repository.ContasPagar;
import br.com.jdrmservices.araujocafe.repository.ContasPagarLancamento;
import br.com.jdrmservices.araujocafe.repository.ContasReceber;
import br.com.jdrmservices.araujocafe.repository.ContasReceberLancamento;

@Service
public class ServiceDashboard {

	@Autowired
	private ContasPagar contasPagar;
	
	@Autowired
	private ContasPagarLancamento contasPagarLancamento;
	
	@Autowired
	private ContasReceber contasReceber;
	
	@Autowired
	private ContasReceberLancamento contasReceberLancamento;
	
	@Autowired
	private Agendas agendas;
	
	public Long totalContasPagarHoje() {
		return (long) contasPagar.findByDataVencimento(LocalDate.now()).size();
	}

	public Long totalContasReceberHoje() {
		return (long) contasReceber.findByDataVencimento(LocalDate.now()).size();
	}
	
	public Long totalCompromissosHoje() {
		return (long) agendas.findByDataCompromisso(LocalDate.now()).size();
	}
	
	public BigDecimal valorTotalDespesasNoMes() {
		return contasPagar.valorTotalDespesasNoMes();
	}

	public BigDecimal valorTotalDespesasHoje() {
		return contasPagar.valorTotalDespesasHoje();
	}
	
	public BigDecimal totalPagoNoMes() {
		return contasPagarLancamento.totalPagoNoMes();
	}
	
	public BigDecimal valorTotalReceitasNoMes() {
		return contasReceber.valorTotalReceitasNoMes();
	}

	public BigDecimal valorTotalReceitasHoje() {
		return contasReceber.valorTotalReceitasHoje();
	}
	
	public BigDecimal valorTotalRecebidoNoMes() {
		return contasReceberLancamento.valorTotalRecebidoNoMes();
	}
}
