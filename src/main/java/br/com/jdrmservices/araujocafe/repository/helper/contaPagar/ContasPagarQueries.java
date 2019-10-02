package br.com.jdrmservices.araujocafe.repository.helper.contaPagar;

import java.math.BigDecimal;
import java.util.List;

import br.com.jdrmservices.araujocafe.dto.TotalDespesasNoAno;
import br.com.jdrmservices.araujocafe.dto.TotalDespesasNoMes;

public interface ContasPagarQueries {
	public BigDecimal valorTotalDespesasNoMes();
	public BigDecimal valorTotalDespesasHoje();
	public List<TotalDespesasNoMes> totalDespesasNoMes();
	public List<TotalDespesasNoAno> totalDespesasNoAno();
}
