package br.com.jdrmservices.araujocafe.repository.filter;

import br.com.jdrmservices.araujocafe.model.Cliente;
import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;

public class ProcessoFilter {
	
	private String numeroProcesso;
	private Cliente cliente;
	private String nomeParteContraria;
	private StatusProcesso status;
	
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getNomeParteContraria() {
		return nomeParteContraria;
	}
	
	public void setNomeParteContraria(String nomeParteContraria) {
		this.nomeParteContraria = nomeParteContraria;
	}
	
	public StatusProcesso getStatus() {
		return status;
	}
	
	public void setStatus(StatusProcesso status) {
		this.status = status;
	}
	
}
