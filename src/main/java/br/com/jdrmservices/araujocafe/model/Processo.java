package br.com.jdrmservices.araujocafe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.jdrmservices.araujocafe.model.constants.Foro;
import br.com.jdrmservices.araujocafe.model.constants.NaturezaAcao;
import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;
import br.com.jdrmservices.araujocafe.model.constants.Vara;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Entity
@Table(name = "processo")
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Número do processo é obrigatório")
	@Size(max = 100, message = "A quantidade máxima de caracteres é 100")
	@Column(name = "numero_processo")
	private String numeroProcesso;
	
	@DateTimeFormat(pattern = Constantes.FORMAT_DATE)
	@Column(name = "data_cadastro", columnDefinition = "DATE")
	private LocalDate dataCadastro = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	@NotNull(message = "Cliente é obrigatório")
	private Cliente cliente;
	
	// Juizo de tramitação
	@NotNull(message = "Foro é obrigatório")
	@Enumerated(EnumType.STRING)
	private Foro foro;
	
	@ManyToOne
	@JoinColumn(name = "estado_codigo_tramitacao")
	@NotNull(message = "Estado do juízo de tramitação é obrigatório")
	private Estado estadoTramitacao;
	
	@ManyToOne
	@JoinColumn(name = "cidade_codigo_tramitacao")
	@NotNull(message = "Cidade do juízo de tramitação é obrigatório")
	private Cidade cidadeTramitacao;
	
	@NotNull(message = "Vara é obrigatório")
	@Enumerated(EnumType.STRING)
	private Vara vara;
	
	private String tombo;
	
	@Column(name = "cor_pasta")
	private String corPasta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "natureza_acao")
	private NaturezaAcao naturezaAcao;
	
	@Column(name = "nome_parte_contraria")
	private String nomeParteContraria;
	
	@Column(name = "cep_parte_contraria")
	private String cepParteContraria;

	@ManyToOne
	@JoinColumn(name = "estado_codigo")
	@NotNull(message = "Estado é obrigatório")
	private Estado estadoParteContraria;
	
	@ManyToOne
	@JoinColumn(name = "cidade_codigo")
	@NotNull(message = "Cidade é obrigatório")
	private Cidade cidadeParteContraria;
	
	@Column(name = "bairro_parte_contraria")
	private String bairroParteContraria;
	
	@Column(name = "logradouro_parte_contraria")
	private String logradouroParteContraria;
	
	@Column(name = "numero_parte_contraria")
	private String numeroParteContraria;
	
	@Column(name = "rg_parte_contraria")
	private String rgParteContraria;
	
	@Column(name = "cpf_parte_contraria")
	private String cpfParteContraria;
	
	@Column(name = "profissao_parte_contraria")
	private String profissaoParteContraria;
	
	@Column(name = "tipo_acao")
	@NotBlank(message = "Tipo da ação é obrigatória")
	private String tipoAcao;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status é obrigatório")
	private StatusProcesso status;
	
	@NotBlank(message = "Resumo é obrigatório")
	private String resumo;
	
	/* documentos apresentados */
	@ManyToMany
	@JoinTable(name = "processo_documentos",
		joinColumns = @JoinColumn(name = "codigo_processo"),
		inverseJoinColumns = @JoinColumn(name = "codigo_documentos_apresentados"))
	private List<DocumentoApresentado> documentoApresentado;
	
	/* despesas do processo */
	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
	private List<Despesa> despesas = new ArrayList<>();
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}

	public Estado getEstadoTramitacao() {
		return estadoTramitacao;
	}

	public void setEstadoTramitacao(Estado estadoTramitacao) {
		this.estadoTramitacao = estadoTramitacao;
	}

	public Cidade getCidadeTramitacao() {
		return cidadeTramitacao;
	}

	public void setCidadeTramitacao(Cidade cidadeTramitacao) {
		this.cidadeTramitacao = cidadeTramitacao;
	}

	public Vara getVara() {
		return vara;
	}

	public void setVara(Vara vara) {
		this.vara = vara;
	}

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public String getCorPasta() {
		return corPasta;
	}

	public void setCorPasta(String corPasta) {
		this.corPasta = corPasta;
	}

	public NaturezaAcao getNaturezaAcao() {
		return naturezaAcao;
	}

	public void setNaturezaAcao(NaturezaAcao naturezaAcao) {
		this.naturezaAcao = naturezaAcao;
	}

	public String getNomeParteContraria() {
		return nomeParteContraria;
	}

	public void setNomeParteContraria(String nomeParteContraria) {
		this.nomeParteContraria = nomeParteContraria;
	}

	public String getCepParteContraria() {
		return cepParteContraria;
	}

	public void setCepParteContraria(String cepParteContraria) {
		this.cepParteContraria = cepParteContraria;
	}

	public Estado getEstadoParteContraria() {
		return estadoParteContraria;
	}

	public void setEstadoParteContraria(Estado estadoParteContraria) {
		this.estadoParteContraria = estadoParteContraria;
	}

	public Cidade getCidadeParteContraria() {
		return cidadeParteContraria;
	}

	public void setCidadeParteContraria(Cidade cidadeParteContraria) {
		this.cidadeParteContraria = cidadeParteContraria;
	}

	public String getBairroParteContraria() {
		return bairroParteContraria;
	}

	public void setBairroParteContraria(String bairroParteContraria) {
		this.bairroParteContraria = bairroParteContraria;
	}

	public String getLogradouroParteContraria() {
		return logradouroParteContraria;
	}

	public void setLogradouroParteContraria(String logradouroParteContraria) {
		this.logradouroParteContraria = logradouroParteContraria;
	}

	public String getNumeroParteContraria() {
		return numeroParteContraria;
	}

	public void setNumeroParteContraria(String numeroParteContraria) {
		this.numeroParteContraria = numeroParteContraria;
	}

	public String getRgParteContraria() {
		return rgParteContraria;
	}

	public void setRgParteContraria(String rgParteContraria) {
		this.rgParteContraria = rgParteContraria;
	}

	public String getCpfParteContraria() {
		return cpfParteContraria;
	}

	public void setCpfParteContraria(String cpfParteContraria) {
		this.cpfParteContraria = cpfParteContraria;
	}

	public String getProfissaoParteContraria() {
		return profissaoParteContraria;
	}

	public void setProfissaoParteContraria(String profissaoParteContraria) {
		this.profissaoParteContraria = profissaoParteContraria;
	}

	public String getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public StatusProcesso getStatus() {
		return status;
	}

	public void setStatus(StatusProcesso status) {
		this.status = status;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<DocumentoApresentado> getDocumentoApresentado() {
		return documentoApresentado;
	}

	public void setDocumentoApresentado(List<DocumentoApresentado> documentoApresentado) {
		this.documentoApresentado = documentoApresentado;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
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
		Processo other = (Processo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
