package br.com.jdrmservices.araujocafe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.jdrmservices.araujocafe.model.constants.Status;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Entity
@Table(name = "conta_receber")
public class ContaReceber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@OneToOne
	@NotNull(message = "Cliente é obrigatório")
	private Cliente cliente;
	
	@NotBlank(message = "Descrição é obrigatória")
	private String descricao;
	
	private BigDecimal valor = BigDecimal.ZERO;
	
	@Column(name = "numero_processo")
	@NotNull(message = "Número do processo é obrigatório")
	private String numeroProcesso;
	
	@DateTimeFormat(pattern = Constantes.FORMAT_DATE)
	@Column(name = "data_vencimento", columnDefinition = "DATE")
	@NotNull(message = "Data de vencimento é obbrigatório")
	private LocalDate dataVencimento = LocalDate.now();
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status é obrigatório")
	private Status status;

	public boolean isNova() {
		return codigo == null;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		ContaReceber other = (ContaReceber) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
