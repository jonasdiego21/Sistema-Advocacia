package br.com.jdrmservices.araujocafe.repository.filter;

public class ClienteFilter {
	
	private String nome;
	private String cpf;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
