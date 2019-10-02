package br.com.jdrmservices.araujocafe.services.exceptions;

public class NomeClienteJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NomeClienteJaCadastradoException(String mensagem) {
		super(mensagem);
	}	
	
}
