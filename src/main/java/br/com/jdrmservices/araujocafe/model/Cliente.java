package br.com.jdrmservices.araujocafe.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.jdrmservices.araujocafe.model.constants.EstadoCivil;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nome")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@Column(name = "cep")
	@NotBlank(message = "CEP é obrigatório")
	private String cep;
	
	@Column(name = "estado")
	@NotBlank(message = "UF é obrigatório")
	private String estado;
	
	@Column(name = "cidade")
	@NotBlank(message = "Cidade é obrigatório")
	private String cidade;
	
	@Column(name = "logradouro")
	@NotBlank(message = "Logradouro é obrigatório")
	private String logradouro;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "estado_civil")
	@NotNull(message = "Estado Civil é obrigatório")
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	@Column(name = "profissao")
	@NotBlank(message = "Profissão é obrigatório")
	private String profissao;
	
	@Column(name = "rg")
	@NotBlank(message = "RG é obrigatório")
	private String rg;
	
	@Column(name = "cpf")
	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF digitado é inválido")
	private String cpf;
	
	@Column(name = "ctps")
	private String ctps;
	
	@Column(name = "titulo_eleitor")
	private String tituloEleitor;
	
	@Column(name = "telefone_fixo")
	private String telefoneFixo;
	
	@Column(name = "celular_principal")
	private String celularPrincipal;
	
	@Column(name = "celular_secundario")
	private String celularSecundario;
	
	@DateTimeFormat(pattern = Constantes.FORMAT_DATE)
	@NotNull(message = "Data de nascimento é obrigatória")
	@Column(name = "data_nascimento", columnDefinition = "DATE")
	private LocalDate dataNascimento;
	
	@Column(name = "email")
	private String email;

	public boolean isNovo() {
		return codigo == null;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getCelularPrincipal() {
		return celularPrincipal;
	}

	public void setCelularPrincipal(String celularPrincipal) {
		this.celularPrincipal = celularPrincipal;
	}

	public String getCelularSecundario() {
		return celularSecundario;
	}

	public void setCelularSecundario(String celularSecundario) {
		this.celularSecundario = celularSecundario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
