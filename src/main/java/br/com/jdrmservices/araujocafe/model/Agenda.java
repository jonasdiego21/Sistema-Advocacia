package br.com.jdrmservices.araujocafe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.jdrmservices.araujocafe.util.Constantes;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Titulo é obrigatório")
	private String titulo;
	
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	
	@DateTimeFormat(pattern = Constantes.FORMAT_DATE)
	@Column(name = "data_compromisso", columnDefinition = "DATE")
	@NotNull(message = "Data é obrigatório")
	private LocalDate dataCompromisso = LocalDate.now();
	
	@DateTimeFormat(pattern = Constantes.FORMAT_TIME)
	@Column(name = "hora_compromisso", columnDefinition = "TIME")
	@NotNull(message = "Hora é obrigatório")
	private LocalTime horaCompromisso = LocalTime.now();

	public boolean isNova() {
		return codigo == null;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCompromisso() {
		return dataCompromisso;
	}

	public void setDataCompromisso(LocalDate dataCompromisso) {
		this.dataCompromisso = dataCompromisso;
	}

	public LocalTime getHoraCompromisso() {
		return horaCompromisso;
	}

	public void setHoraCompromisso(LocalTime horaCompromisso) {
		this.horaCompromisso = horaCompromisso;
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
		Agenda other = (Agenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
