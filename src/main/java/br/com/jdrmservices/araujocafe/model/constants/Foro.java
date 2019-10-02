package br.com.jdrmservices.araujocafe.model.constants;

public enum Foro {

	FEDERAL("FEDERAL"),
	ESTADUAL("ESTADUAL"),
	TRABALHISTA("TRABALHISTA");
	
	private String descricao;
	
	Foro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
