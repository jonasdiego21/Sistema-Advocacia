package br.com.jdrmservices.araujocafe.dto;

import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;

public class TotalProcessosPorStatus {

	private StatusProcesso status;
	private Integer total;
	
	public TotalProcessosPorStatus(StatusProcesso status, Integer total) {
		this.status = status;
		this.total = total;
	}

	public StatusProcesso getStatus() {
		return status;
	}

	public void setStatus(StatusProcesso status) {
		this.status = status;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
