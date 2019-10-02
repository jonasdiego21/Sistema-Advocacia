package br.com.jdrmservices.araujocafe.services.exceptions;

public class NomeUsuarioJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NomeUsuarioJaCadastradoException(String mensagem) {
		super(mensagem);
	}
	
}
