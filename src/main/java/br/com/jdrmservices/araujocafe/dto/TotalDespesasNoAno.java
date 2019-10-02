package br.com.jdrmservices.araujocafe.dto;

import java.math.BigDecimal;

public class TotalDespesasNoAno {
	
	private String ano;
	private BigDecimal total;
	
	public TotalDespesasNoAno(String ano, BigDecimal total) {
		this.ano = ano;
		this.total = total;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
