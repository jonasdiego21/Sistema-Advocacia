package br.com.jdrmservices.araujocafe.model.constants;

public enum StatusProcesso {
	
	PROTOCOLADO("PROTOCOLADO"),
	AGUARDANDO_AUDIENCIA_PERICIA("AGUARDANDO AUDIÊNCIA/PERÍCIA"),
	JULGADO_PRIMEIRA_INSTANCIA("JULGADO 1º INSTÂNCIA"),
	JULGADO_SEGUNDA_INSTANCIA("JULGADO 2º INSTÂNCIA"),
	TRANSITADO_EM_JULGADO("TRANSITADO EM JULGADO"),
	EM_EXECUCAO("EM EXECUÇÃO"),
	ENCERRADO("ENCERRADO"),
	ARQUIVADO("ARQUIVADO");
	
	public String descricao;
	
	private StatusProcesso(String descricao) {
		this.descricao = descricao;
	}
}
