package br.com.jdrmservices.araujocafe.model.constants;

public enum EstadoCivil {

	SOLTEIRO("SOLTEIRO(A)"),
	CASADO("CASADO(A)"),
	VIUVO("VIÚVO(A)"),
	DIVORCIADO("DIVORCIADO(A)");
	
	public String descricao;
	
	EstadoCivil(String descricao) {
		this.descricao = descricao;
	}
	
}
