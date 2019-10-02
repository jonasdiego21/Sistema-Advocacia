package br.com.jdrmservices.araujocafe.model.constants;

public enum NaturezaAcao {
	CIVEL("CÍVEL"),
	CRIMINAL("CRIMINAL"),
	PREVIDENCIARIO("PREVIDENCIÁRIO"),
	TRABALHISTA("TRABALHISTA"),
	CONSUMIDOR("CONSUMIDOR"),
	FAZENDA_PUBLICA("FAZENDA PÚBLICA MUNICIPAL"),
	NOMEACAO_DATIVO("NOMEAÇÃO DATIVO"),
	TRIBUTARIO("TRIBUTÁRIO");
	
	public String descricao;
	
	private NaturezaAcao(String descricao) {
		this.descricao = descricao;
	}
}
