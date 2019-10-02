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
@Table(name = "conta_receber_lancamento")
public class ContaReceberLancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "conta_receber_codigo")
	@NotNull(message = "Honorário é obrigatória")
	private ContaReceber contaReceberCodigo;
	
	@Column(name = "valor_recebido")
	private BigDecimal valorRecebido = BigDecimal.ZERO;
	
	@DateTimeFormat(pattern = Constantes.FORMAT_DATE)
	@Column(name = "data_recebimento", columnDefinition = "DATE")
	@NotNull(message = "Data do recebimento é obrigatória")
	private LocalDate dataRecebimento = LocalDate.now();
	
	public boolean isNova() {
		return codigo == null;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public ContaReceber getContaReceberCodigo() {
		return contaReceberCodigo;
	}

	public void setContaReceberCodigo(ContaReceber contaReceberCodigo) {
		this.contaReceberCodigo = contaReceberCodigo;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
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
		ContaReceberLancamento other = (ContaReceberLancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
