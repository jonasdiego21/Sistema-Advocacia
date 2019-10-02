package br.com.jdrmservices.araujocafe.services.exceptions;

public class NumeroProcessoJaCadastradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NumeroProcessoJaCadastradoException(String mensagem) {
		super(mensagem);
	}
}
