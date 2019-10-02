package br.com.jdrmservices.araujocafe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.jdrmservices.araujocafe.util.Constantes;

@Entity
@Table(name = "conta_pagar_lancamento")
public class ContaPagarLancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "conta_pagar_codigo")
	@NotNull(message = "Despesa é obrigatória")
	private ContaPagar contaPagarCodigo;
	
	@Column(name = "valor_pago")
	private BigDecimal valorPago = BigDecimal.ZERO;
	
	@DateTimeFormat(pattern = Constantes.FORMAT_DATE)
	@Column(name = "data_pagamento", columnDefinition = "DATE")
	@NotNull(message = "Data do pagamento é obrigatória")
	private LocalDate dataPagamento = LocalDate.now();
	
	public boolean isNova() {
		return codigo == null;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public ContaPagar getContaPagarCodigo() {
		return contaPagarCodigo;
	}

	public void setContaPagarCodigo(ContaPagar contaPagarCodigo) {
		this.contaPagarCodigo = contaPagarCodigo;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaPagarLancamento other = (ContaPagarLancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
