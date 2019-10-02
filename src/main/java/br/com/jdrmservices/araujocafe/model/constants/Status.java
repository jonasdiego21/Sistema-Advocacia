package br.com.jdrmservices.araujocafe.model.constants;

public enum Status {

	DEVENDO("Devendo"),
	CANCELADO("Cancelado"),
	QUITADA("Quitado(a)");
	
	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
