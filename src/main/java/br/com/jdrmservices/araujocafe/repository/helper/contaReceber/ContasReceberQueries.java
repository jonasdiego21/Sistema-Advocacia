package br.com.jdrmservices.araujocafe.repository.helper.contaReceber;

import java.math.BigDecimal;
import java.util.List;

import br.com.jdrmservices.araujocafe.dto.TotalReceitasNoAno;
import br.com.jdrmservices.araujocafe.dto.TotalReceitasNoMes;

public interface ContasReceberQueries {
	public BigDecimal valorTotalReceitasNoMes();
	public BigDecimal valorTotalReceitasHoje();
	public List<TotalReceitasNoMes> totalReceitasNoMes();
	public List<TotalReceitasNoAno> totalReceitasNoAno();
}
