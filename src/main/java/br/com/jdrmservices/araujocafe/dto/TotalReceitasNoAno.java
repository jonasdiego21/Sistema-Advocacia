package br.com.jdrmservices.araujocafe.dto;

import java.math.BigDecimal;

public class TotalReceitasNoAno {
	
	private String ano;
	private BigDecimal total;
	
	public TotalReceitasNoAno(String ano, BigDecimal total) {
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
